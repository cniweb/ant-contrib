/*
 * Copyright (c) 2001-2004 Ant-Contrib project.  All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.antcontrib.antclipse;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Property;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Path.PathElement;
import org.apache.tools.ant.util.RegexpPatternMapper;
import org.xml.sax.AttributeList;
import org.xml.sax.HandlerBase;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Support class for the Antclipse task. Basically, it takes the .classpath
 * Eclipse file and feeds a SAX parser. The handler is slightly different
 * according to what we want to obtain (a classpath or a fileset)
 *
 * @author Adrian Spinei aspinei@myrealbox.com
 * @version $Revision: 1.2 $
 * @since Ant 1.5
 */
public class ClassPathTask extends Task {
	abstract class AbstractCustomHandler extends HandlerBase {
		protected static final String ATTRNAME_PATH = "path";
		protected static final String ATTRNAME_KIND = "kind";
		protected static final String ATTR_LIB = "lib";
		protected static final String ATTR_SRC = "src";
		protected static final String ATTR_OUTPUT = "output";
		protected static final String EMPTY = "";
		protected String projDir;
	}

	class FileSetCustomHandler extends AbstractCustomHandler {
		private FileSet fileSet = null;

		/**
		 * @param fileSet
		 */
		public FileSetCustomHandler(FileSet fileSet) {
			super();
			this.fileSet = fileSet;
			this.projDir = ClassPathTask.this.getProject().getBaseDir().getAbsolutePath().toString();
		}

		/**
		 * @see org.xml.sax.DocumentHandler#endDocument()
		 */
		@Override
		public void endDocument() throws SAXException {
			super.endDocument();
			if (this.fileSet != null && !this.fileSet.hasPatterns()) {
				this.fileSet.setExcludes("**/*");
				// exclude everything or we'll take all the project dirs
			}
		}

		@Override
		public void startElement(String tag, AttributeList attrs) throws SAXParseException {
			if (tag.equalsIgnoreCase("classpathentry")) {
				// start by checking if the classpath is coherent at all
				final String kind = attrs.getValue(AbstractCustomHandler.ATTRNAME_KIND);
				if (kind == null) {
					throw new BuildException("classpathentry 'kind' attribute is mandatory");
				}
				String path = attrs.getValue(AbstractCustomHandler.ATTRNAME_PATH);
				if (path == null) {
					throw new BuildException("classpathentry 'path' attribute is mandatory");
				}

				// put the outputdirectory in a property
				if (kind.equalsIgnoreCase(AbstractCustomHandler.ATTR_OUTPUT)) {
					final String propName = ClassPathTask.this.idContainer + "outpath";
					final Property property = new Property();
					property.setName(propName);
					property.setValue(path);
					property.setProject(ClassPathTask.this.getProject());
					property.execute();
					if (ClassPathTask.this.verbose) {
						System.out.println("Setting property " + propName + " to value " + path);
					}
				}

				// let's put the last source directory in a property
				if (kind.equalsIgnoreCase(AbstractCustomHandler.ATTR_SRC)) {
					final String propName = ClassPathTask.this.idContainer + "srcpath";
					final Property property = new Property();
					property.setName(propName);
					property.setValue(path);
					property.setProject(ClassPathTask.this.getProject());
					property.execute();
					if (ClassPathTask.this.verbose) {
						System.out.println("Setting property " + propName + " to value " + path);
					}
				}

				if ((kind.equalsIgnoreCase(AbstractCustomHandler.ATTR_SRC) && ClassPathTask.this.includeSource)
						|| (kind.equalsIgnoreCase(AbstractCustomHandler.ATTR_OUTPUT)
								&& ClassPathTask.this.includeOutput)
						|| (kind.equalsIgnoreCase(AbstractCustomHandler.ATTR_LIB) && ClassPathTask.this.includeLibs)) {
					// all seem fine
					// check the includes
					String[] inclResult = new String[] { "all included" };
					if (ClassPathTask.this.irpm != null) {
						inclResult = ClassPathTask.this.irpm.mapFileName(path);
					}
					String[] exclResult = null;
					if (ClassPathTask.this.erpm != null) {
						exclResult = ClassPathTask.this.erpm.mapFileName(path);
					}
					if (inclResult != null && exclResult == null) {
						// THIS is the specific code
						if (kind.equalsIgnoreCase(AbstractCustomHandler.ATTR_OUTPUT)) {
							// we have included output so let's build a new
							// fileset
							final FileSet outFileSet = new FileSet();
							final String newReference = ClassPathTask.this.idContainer + "-"
									+ path.replace(File.separatorChar, '-');
							ClassPathTask.this.getProject().addReference(newReference, outFileSet);
							if (ClassPathTask.this.verbose) {
								System.out.println("Created new fileset " + newReference
										+ " containing all the files from the output dir " + this.projDir
										+ File.separator + path);
							}
							outFileSet.setDefaultexcludes(false);
							outFileSet.setDir(new File(this.projDir + File.separator + path));
							outFileSet.setIncludes("**/*"); // get everything
						} else if (kind.equalsIgnoreCase(AbstractCustomHandler.ATTR_SRC)) {
							// we have included source so let's build a new
							// fileset
							final FileSet srcFileSet = new FileSet();
							final String newReference = ClassPathTask.this.idContainer + "-"
									+ path.replace(File.separatorChar, '-');
							ClassPathTask.this.getProject().addReference(newReference, srcFileSet);
							if (ClassPathTask.this.verbose) {
								System.out.println("Created new fileset " + newReference
										+ " containing all the files from the source dir " + this.projDir
										+ File.separator + path);
							}
							srcFileSet.setDefaultexcludes(false);
							srcFileSet.setDir(new File(this.projDir + File.separator + path));
							srcFileSet.setIncludes("**/*"); // get everything
						} else {
							// not otuptut, just add file after file to the
							// fileset
							final File file = new File(
									this.fileSet.getDir(ClassPathTask.this.getProject()) + "/" + path);
							if (file.isDirectory()) {
								path += "/**/*";
							}
							if (ClassPathTask.this.verbose) {
								System.out.println("Adding  " + path + " to fileset " + ClassPathTask.this.idContainer
										+ " at " + this.fileSet.getDir(ClassPathTask.this.getProject()));
							}
							this.fileSet.setIncludes(path);
						}
					}
				}
			}
		}
	}

	class PathCustomHandler extends AbstractCustomHandler {
		private Path path = null;

		/**
		 * @param path
		 *            the path to add files
		 */
		public PathCustomHandler(Path path) {
			super();
			this.path = path;
		}

		@Override
		public void startElement(String tag, AttributeList attrs) throws SAXParseException {
			if (tag.equalsIgnoreCase("classpathentry")) {
				// start by checking if the classpath is coherent at all
				final String kind = attrs.getValue(AbstractCustomHandler.ATTRNAME_KIND);
				if (kind == null) {
					throw new BuildException("classpathentry 'kind' attribute is mandatory");
				}
				final String path = attrs.getValue(AbstractCustomHandler.ATTRNAME_PATH);
				if (path == null) {
					throw new BuildException("classpathentry 'path' attribute is mandatory");
				}

				// put the outputdirectory in a property
				if (kind.equalsIgnoreCase(AbstractCustomHandler.ATTR_OUTPUT)) {
					final String propName = ClassPathTask.this.idContainer + "outpath";
					final Property property = new Property();
					property.setName(propName);
					property.setValue(path);
					property.setProject(ClassPathTask.this.getProject());
					property.execute();
					if (ClassPathTask.this.verbose) {
						System.out.println("Setting property " + propName + " to value " + path);
					}
				}

				// let's put the last source directory in a property
				if (kind.equalsIgnoreCase(AbstractCustomHandler.ATTR_SRC)) {
					final String propName = ClassPathTask.this.idContainer + "srcpath";
					final Property property = new Property();
					property.setName(propName);
					property.setValue(path);
					property.setProject(ClassPathTask.this.getProject());
					property.execute();
					if (ClassPathTask.this.verbose) {
						System.out.println("Setting property " + propName + " to value " + path);
					}
				}

				if ((kind.equalsIgnoreCase(AbstractCustomHandler.ATTR_SRC) && ClassPathTask.this.includeSource)
						|| (kind.equalsIgnoreCase(AbstractCustomHandler.ATTR_OUTPUT)
								&& ClassPathTask.this.includeOutput)
						|| (kind.equalsIgnoreCase(AbstractCustomHandler.ATTR_LIB) && ClassPathTask.this.includeLibs)) {
					// all seem fine
					// check the includes
					String[] inclResult = new String[] { "all included" };
					if (ClassPathTask.this.irpm != null) {
						inclResult = ClassPathTask.this.irpm.mapFileName(path);
					}
					String[] exclResult = null;
					if (ClassPathTask.this.erpm != null) {
						exclResult = ClassPathTask.this.erpm.mapFileName(path);
					}
					if (inclResult != null && exclResult == null) {
						// THIS is the only specific code
						if (ClassPathTask.this.verbose) {
							System.out.println("Adding  " + path + " to classpath " + ClassPathTask.this.idContainer);
						}
						final PathElement element = this.path.createPathElement();
						element.setLocation(new File(path));
					}
				}
			}
		}
	}

	public static final String TARGET_CLASSPATH = "classpath";
	public static final String TARGET_FILESET = "fileset";
	private String idContainer = "antclipse";
	private boolean includeSource = false; // default, do not include source
	private boolean includeOutput = false; // default, do not include output
											// directory
	private boolean includeLibs = true; // default, include all libraries
	private boolean verbose = false; // default quiet

	RegexpPatternMapper irpm = null;

	RegexpPatternMapper erpm = null;

	private String produce = null; // classpath by default

	/**
	 * @see org.apache.tools.ant.Task#execute()
	 */
	@Override
	public void execute() throws BuildException {
		if (!ClassPathTask.TARGET_CLASSPATH.equalsIgnoreCase(this.produce)
				&& !ClassPathTask.TARGET_FILESET.equals(this.produce)) {
			throw new BuildException("Mandatory target must be either '" + ClassPathTask.TARGET_CLASSPATH + "' or '"
					+ ClassPathTask.TARGET_FILESET + "'");
		}
		final ClassPathParser parser = new ClassPathParser();
		AbstractCustomHandler handler;
		if (ClassPathTask.TARGET_CLASSPATH.equalsIgnoreCase(this.produce)) {
			final Path path = new Path(this.getProject());
			this.getProject().addReference(this.idContainer, path);
			handler = new PathCustomHandler(path);
		} else {
			final FileSet fileSet = new FileSet();
			this.getProject().addReference(this.idContainer, fileSet);
			fileSet.setDir(new File(this.getProject().getBaseDir().getAbsolutePath().toString()));
			handler = new FileSetCustomHandler(fileSet);
		}
		parser.parse(new File(this.getProject().getBaseDir().getAbsolutePath(), ".classpath"), handler);
	}

	/**
	 * Setter for task parameter
	 *
	 * @param excludes
	 *            A regexp for files to exclude. It is taken into account only
	 *            when producing a classpath, doesn't work on source or output
	 *            files. It is a real regexp, not a "*" expression.
	 */
	public void setExcludes(String excludes) {
		if (excludes != null) {
			this.erpm = new RegexpPatternMapper();
			this.erpm.setFrom(excludes);
			this.erpm.setTo("."); // mandatory
		} else {
			this.erpm = null;
		}
	}

	/**
	 * Setter for task parameter
	 *
	 * @param idContainer
	 *            The refid which will serve to identify the deliverables. When
	 *            multiple filesets are produces, their refid is a concatenation
	 *            between this value and something else (usually obtained from a
	 *            path). Default "antclipse"
	 */
	public void setIdContainer(String idContainer) {
		this.idContainer = idContainer;
	}

	/**
	 * Setter for task parameter
	 *
	 * @param includeLibs
	 *            Boolean, whether to include or not the project libraries.
	 *            Default is true.
	 */
	public void setIncludeLibs(boolean includeLibs) {
		this.includeLibs = includeLibs;
	}

	/**
	 * Setter for task parameter
	 *
	 * @param includeOutput
	 *            Boolean, whether to include or not the project output
	 *            directories. Default is false.
	 */
	public void setIncludeOutput(boolean includeOutput) {
		this.includeOutput = includeOutput;
	}

	/**
	 * Setter for task parameter
	 *
	 * @param includes
	 *            A regexp for files to include. It is taken into account only
	 *            when producing a classpath, doesn't work on source or output
	 *            files. It is a real regexp, not a "*" expression.
	 */
	public void setIncludes(String includes) {
		if (includes != null) {
			this.irpm = new RegexpPatternMapper();
			this.irpm.setFrom(includes);
			this.irpm.setTo("."); // mandatory
		} else {
			this.irpm = null;
		}
	}

	/**
	 * Setter for task parameter
	 *
	 * @param includeSource
	 *            Boolean, whether to include or not the project source
	 *            directories. Default is false.
	 */
	public void setIncludeSource(boolean includeSource) {
		this.includeSource = includeSource;
	}

	/**
	 * Setter for task parameter
	 *
	 * @param produce
	 *            This parameter tells the task wether to produce a "classpath"
	 *            or a "fileset" (multiple filesets, as a matter of fact).
	 */
	public void setproduce(String produce) {
		this.produce = produce;
	}

	/**
	 * Setter for task parameter
	 *
	 * @param verbose
	 *            Boolean, telling the app to throw some info during each step.
	 *            Default is false.
	 */
	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}
}

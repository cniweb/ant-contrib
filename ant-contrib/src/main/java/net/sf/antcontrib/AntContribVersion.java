/*
 * Copyright (c) 2004 Ant-Contrib project.  All rights reserved.
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
package net.sf.antcontrib;

/**
 * @author Dean Hiller
 *
 *         To change the template for this generated type comment go to Window -
 *         Preferences - Java - Code Generation - Code and Comments
 */
public class AntContribVersion {

	/**
	 * The main program for MockVersion that prints the version info from the
	 * manifest file.
	 *
	 * @param args
	 *            Ignores all arguments.
	 */
	public static void main(String[] args) {
		final AntContribVersion version = new AntContribVersion(AntContribVersion.class);
		System.out.println("" + version);
	}

	private final Package thePackage;

	/**
	 * Constructor that takes a class to get the version information from out of
	 * the manifest. Uses the class's package to retrieve the manifest version
	 * info.
	 * 
	 * @param c
	 *            The Class on whose package to use to get version info.
	 */
	public AntContribVersion(Class c) {
		final String name = c.getName();
		final int index = name.lastIndexOf(".");

		if (index < 0) {
			throw new RuntimeException("This class is the default package and can't be to use this feature");
		}

		final String packageName = name.substring(0, index);
		this.thePackage = Package.getPackage(packageName);
	}

	/**
	 * Prints the version info the MockVersion represents.
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String version = "\nVersion of Ant Contrib...";
		version += "\ntitle=" + this.thePackage.getImplementationTitle();
		version += "\nwebsite=" + this.thePackage.getImplementationVendor();
		version += "\nversion=" + this.thePackage.getImplementationVersion() + "\n";

		return version;
	}
}

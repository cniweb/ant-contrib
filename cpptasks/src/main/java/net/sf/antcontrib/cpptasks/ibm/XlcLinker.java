/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001 The Ant-Contrib project.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Ant-Contrib project (http://sourceforge.net/projects/ant-contrib)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "Ant-Contrib"
 *    must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Ant-Contrib"
 *    nor may "Ant-Contrib" appear in their names without prior written
 *    permission of the Ant-Contrib project.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE ANT-CONTRIB PROJECT OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 */
package net.sf.antcontrib.cpptasks.ibm;

import java.util.Vector;

import net.sf.antcontrib.cpptasks.compiler.LinkType;
import net.sf.antcontrib.cpptasks.compiler.Linker;
import net.sf.antcontrib.cpptasks.gcc.AbstractLdLinker;
import net.sf.antcontrib.cpptasks.gcc.GccLibrarian;

/**
 * Adapter for IBM(r) Visual Age(tm) Linker for AIX(tm)
 *
 * @author Curt Arnold
 */
public final class XlcLinker extends AbstractLdLinker
{
    private static final String[] objFiles = new String[]
      { ".o", ".a", ".lib",".dll", ".so", ".sl"};
    private static final String[] discardFiles = new String[]
      { };

    private static final XlcLinker instance =
      new XlcLinker("ld", objFiles, discardFiles, "", "");
    private static final XlcLinker dllLinker =
      new XlcLinker("ld", objFiles, discardFiles, "lib", ".so");

    private XlcLinker(String command, String[] extensions,
        String[] ignoredExtensions, String outputPrefix,
        String outputSuffix) {
    	super(command, "-qversion", extensions, ignoredExtensions,
          outputPrefix, outputSuffix,false,null);
    }

    public static XlcLinker getInstance() {
        return instance;
    }

    public void addImpliedArgs(boolean debug, LinkType linkType, Vector args) {
      if(debug) {
        //args.addElement("-g");
      }
      if(linkType.isSharedLibrary()) {
        args.addElement("-bdynamic");
        args.addElement("-G");
        args.addElement("-bnoentry");
        args.addElement("-bexpall");
        args.addElement("-lc_r");
      }
    }


    public Linker getLinker(LinkType type) {
      if(type.isStaticLibrary()) {
        return GccLibrarian.getInstance();
      }
      if(type.isSharedLibrary()) {
        return dllLinker;
      }
      return instance;
    }
}
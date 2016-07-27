Classes folder
==============

This folder has been provided as a convenience for
placing user class files.  JSP beans or servlet
class files can be placed here.  If the class is
part of a package, create the package hierarchy
starting in this folder.

Note that any .jar files in this folder will not
be loaded!  If your application requires loading
additional Jar files, configure a folder "server.classDir"
in the web configuration file.  Details on how to
configure the web server may be found in the online
documentation in the "Server Configuration" section.

All classes in the default context, and ION server classes,
can be re-loaded by entering the URL

http://Kishore/_reload

The default web.ini supplied with the installation
pre-configures the URL above for class reloading
for the default context.

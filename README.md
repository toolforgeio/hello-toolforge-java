# HELLO TOOLFORGE

This project is an example implementation of a
[ToolForge](https://app.toolforge.io/) tool in Java. It uses the [ToolForge
Maven plugin](https://github.com/toolforgeio/toolforge-maven-plugin) to
generate its argument parser from its manifest. It also uses the excellent
[Fabric8 Docker Maven plugin](https://dmp.fabric8.io/) to build its
[Docker](https://www.docker.com/) container for pushing into ToolForge. Simply
running the following command will generate the required Docker image:

    mvn clean compile install
    
To adapt this example into a real tool, the user should:

1. Create a ToolForge account and create a new container. Call it whatever
   you like! Note the container's container location and ID.
2. Update the POM to reflect the tool's real GAV, SCM, ToolForge tool path,
   target package, main class, and so on.
3. Update the Dockerfile to reflect the container's name and environment.
4. Update the manifest to reflect the tool's inputs, outputs, and parameters.
5. Write the code to implement the tool.
6. Build, push, and run in ToolForge!
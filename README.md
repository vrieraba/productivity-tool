# Productivity Tool

Build Project
=============
1. Run docker
2. Maven Install

Run Project in docker
=====================
- Only run
docker run -v c:/Users/your_user/ProductivityToolData:/mnt/ProductivityToolData -p 8080:8080 -t vriera/productivity-tool
- With debug
docker run -v c:/Users/your_user/ProductivityToolData:/mnt/ProductivityToolData -e "JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n" -p 8080:8080 -p 5005:5005 -t vriera/productivity-tool

Docker commands
================
- Clean <none> images:
docker rmi $(docker images -qa -f 'dangling=true')
- Access container terminal
docker exec -it container_id /bin/sh

Versions
========
1.0.0 - First stable release

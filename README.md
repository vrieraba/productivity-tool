# Productivity Tool

## Run Project in docker
- Only run
```
docker run -v c:/Users/your_user/ProductivityToolData:/mnt/ProductivityToolData -p 8080:8080 -t vriera/productivity-tool
```
- With debug
```
docker run -v c:/Users/your_user/ProductivityToolData:/mnt/ProductivityToolData -e "JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n" -p 8080:8080 -p 5005:5005 -t vriera/productivity-tool
```

## Useful docker commands
- Clean <none> images:
```
docker rmi $(docker images -qa -f 'dangling=true')
```
- Access container terminal
```
docker exec -it container_id /bin/sh
```
- Save docker image (as .tar)
```
docker save -o C:\Users\vrieraba\Desktop\productivity-tool-1.0.0.tar <image name>
```
- Load image in docker
```
docker load -i C:\Users\vrieraba\Desktop\productivity-tool-1.0.0.tar
```

## Releases
- **v1.0.0**
    - First stable release


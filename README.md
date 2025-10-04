# Document Search Application (Vite + React + Spring Boot)

This project contains a **frontend** implemented using [Vite + React](https://vitejs.dev/) and a **backend** implemented using [Spring Boot](https://spring.io/projects/spring-boot).  
Both services are dockerized using docker and can be started easily with the help of **Docker Compose**.

---

## Prerequisites

Before running the Application, make sure you have:

- [Docker](https://docs.docker.com/desktop/setup/install/windows-install/) installed on your machine  

- To verify docker is installed or not, run below commands:

    ```bash
    docker -v
    docker compose version
    ```

## Steps to setup local environment of application

- Clone the application using repository link [Document Search Application](https://github.com/Suryac72/document-search-application.git)
- Create one `.env` File inside above Repository root folder (not in frontend or backend folder) and add content which is shared with you via email
- Run `Docker Desktop` in your machine
- Open `git bash` terminal on `vscode` inside above repository
- Use below command to run application using docker
    ```bash
    ./opensearch.sh #for local command execution like installing packages and creating docker containers
    docker-compose down #for stoping server
    ```
- Access `http://localhost:5173` to access UI
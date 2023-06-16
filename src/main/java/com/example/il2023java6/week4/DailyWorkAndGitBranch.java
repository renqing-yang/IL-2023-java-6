package com.example.il2023java6.week4;


/**
 *  Git branch strategy
 *
 *  master branch (prod env)                --------v1.0
 *                                                  /
 *  release branch (uat / stage env)        ----v1.0-----------------------v1.1
 *                                              /                         /
 *  development branch (dev / qa env)       -------         -------o-----o---o----------o----o
 *                                                  \      / pull request code review + merge
 *  feature branch (local env)                       ------
 *
 *
 *
 *  CI/CD
 *  1. configure git hook / web hook (git login / api key) on dev branch
 *  2. after git merge to dev branch => git send request to ci/cd platform
 *  3. CI/CD is triggered
 *      build => test => report => package docker image => push it to docker repository => deploy to dev / qa env
 *                         |
 *                       SonarQube(server)
 *                       code coverage report
 *                       code security report
 *                       ...
 *
 *  Production Issues
 *  1. check out hotfix branch from master / release branch
 *  2. check logs + fix the issues
 *     (sometimes, explain it to manager / teams in meeting)
 *  3. pull request code review
 *  4. merge code
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Daily work / Agile scrum
 *  1. production backlog (priority)
 *      TODO list:
 *          story: description of the feature
 *          ticket: issues / bugs / fix pipeline / ...
 *  2. sprint 1 week ~ 4 weeks (2 weeks)
 *  3. sprint planning meeting
 *      pull things from backlog
 *      discuss points of each story / ticket
 *          points: fibonacci number / hour based number
 *          fibonacci number : 1 2 3 5 8 13
 *          hour based number : 1point = 0.5 hour / 1 hour / 2 hours / 3 hours
 *      assign stories to developers / leave them on the board(jira, whiteboard)
 *              backlog     |       ongoing     |       finished
 *              x1                 x1(name)
 *  4. daily stand up meeting
 *      checkout new branch => design + develop + test features
 *  5. schedule requirement clarify meetings with manager or ba or qa
 *      schedule design meeting with frontend developer or team leader
 *  6. retrospective meeting / sprint review meeting
 *  7. demo review every few sprints / 2 / 3 / 4 sprints
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Team size
 *  3 ~ 10 people
 *  1. manager
 *  2. product owner
 *  3. team leader
 *  4. frontend developer
 *  5. backend developer
 *  6. diff qa team or qa people in your team
 *  7. 0 or 1 ba
 *  8. shared DBA cross diff teams
 *  9. 0 or 1 scrum master
 *
 *
 *  dev ops team
 *  1. CI/CD pipeline / deployment
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  This Weekend
 *      1. check what is microservice
 *      2. what is Spring Cloud framework
 *      3. what is Spring Cloud Eureka
 *      4. what is Api Gateway
 *      5. what is Circuit Breaker
 *      6. what is Message queue
 *      7. what is client size load balancer
 *      8. what is spring cloud config service
 *
 *
 *  Next Monday
 *      1. microservice
 *
 *
 *  from next week
 *      1:00 pm cdt / 2:00 pm edt / 11:00am pdt
 */
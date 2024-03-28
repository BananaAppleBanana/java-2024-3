package week6;

/**
 * Daily work
 *   Agile Scrum
 *      team size  < 10
 *          full stack developer / backend developer + frontend developer
 *          QA (0 ~ 1, or shared QA team)
 *          Manager (manage 1 ~ N teams)
 *          BA (0 ~ 1)
 *          Scrum Master(0 ~ 1)
 *          DBA (shared cross team)
 *      Sprint : 1 ~ 4 weeks
 *      Production backlog : todo list (priority)
 *          story : todo
 *          ticket  : bug
 *      1. Sprint planning meeting (grooming section)
 *          point :
 *              difficulty level : fib 1 2 3 5 8 13..
 *              hour - score : 1 - 2h / 1 - 1h
 *      2. Daily stand up meeting (15min ~ 30min)
 *      3. Retrospective meeting (sprint review meeting)
 *      4. Demo review meeting every 2 ~ 4 sprints
 *   Development
 *      1. get requirement
 *      2. corner cases
 *      3. design
 *      4. check out new feature branch
 *      5. TDD
 *          design OOD
 *          interface / abstract class
 *          test
 *          impl
 *          run test
 *      6. pull request code review
 *         merge to dev branch
 *      7. trigger ci/cd
 *    Production support
 *      1. read ticket(issues, how to reproduce issue)
 *      2. search co-relation id in centralized log server (Splunk / cloudwatch)
 *      3. check out hot fix branch / fix branch
 *      4. fix issue / rewrite code
 *      5. rewrite test cases
 *      6. pull request code review
 *    Improve api / service performance
 *      1. in log server -> generate P99
 *      2. database slow ?
 *              rewrite query
 *              index
 *              execution plan
 *              hint
 *              material view
 *              + read replica
 *         cache ?
 *              database query cache ?
 *              ttl ?
 *              cache cluster ?
 *         server limit ?
 *              vertical scaling
 *              horizontal scaling
 *              non blocking server
 *         LB ?
 *              check log balancer log
 *         java algorithm / data structure ?
 *              list remove O(N)
 *              map remove O(1)
 *              tree map remove O(logN)
 *              time complexity / space complexity
 *         jvm ?
 *              generation size ?
 *              GC interval ?
 *              GC algorithm ?
 *         network ?
 *              ping server
 *
 *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *   Git branch strategy
 *   master / main / prod branch ------------------------------------------------------         -> cicd -> prod env
 *                                      \           / pull request + merge
 *   hotfix branch                       -----------
 *                                                   \  pull request + merge
 *   uat / release branch        ------------------------------------------------------         -> cicd -> ua env
 *
 *   development branch          ------------------------------------------------------         -> cicd -> dev/qa env
 *                                                  \                / pull request + merge
 *   feature branch                                  ---------------
 *
 *
 *   CI/CD (jenkins , aws pipeline)
 *   git merge -> trigger CI/CD pipeline
 *                      |
 *                      -> build -> test -> Sonarqube report -> package(docker image tag) -> upload to ECR -> deploy ECS
 *                                               |
 *                                         code coverage (jacoco)
 *                                         security (..)
 *
 *   Jenkins + sonarqube / sonar -> deploy to ECS
 */

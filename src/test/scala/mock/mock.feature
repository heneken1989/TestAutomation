Feature: cats stateful crud

  Background:
    * def uuid = function(){ return java.util.UUID.randomUUID() + '' }
    * def cats = {}
    * def createdEmails = {}

  Scenario: pathMatches('/cats') && methodIs('post')
    * def cat = request
    * def id = uuid()
    * cat.id = id
    * cats[id] = cat
    * def response = cat

  Scenario: pathMatches('/cats')
    * def response = $cats.*

  Scenario: pathMatches('/cats/{id}') && methodIs('put')
    * cats[pathParams.id] = request
    * def response = request

  Scenario: pathMatches('/cats/{id}') && methodIs('delete')
    * karate.remove('cats', pathParams.id)
    * def responseDelay = 850

  Scenario: pathMatches('/cats/{id}')
    * def response = cats[pathParams.id]
    * def responseStatus = response ? 200 : 404


  Scenario: pathMatches('/Test/CreateTest') && methodIs('post')
    * call read('classpath:mock/common.feature@name=Initialize Created Emails')
    * def emp = request
    * def email = emp.email
    * karate.appendTo(createdEmails, email)


  Scenario: pathMatches('/Test/CreateRequest') && methodIs('post')
  * def emp = request
  * def email = emp.email
  * karate.appendTo(createdEmails, email)

  

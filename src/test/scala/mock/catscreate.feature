
Feature: Perfomance Test

    @name=CreateUser
    Scenario: CreateUser
        Given url 'http://localhost:5240/Test/CreateTest'
        And request
            """
            {
                "FullName": "a",
                "Email": '#(name)',
                "AmountRequestPerMonth": 100,
                "Avatar": "avatar.png",
                "DepartmentId": 1,
                "EmployeePositionId": 1,
                "SupperVisorId": 3,
                "Password": "1",
                "ConfirmPassword": "1"
            }
            """
        When method post
        Then status 200

 
angular.module('hrmsLogin')
    .controller('loginController', function ($scope, $localStorage, $sessionStorage, $window, loginService) {
        $scope.user = {};
        $scope.user = $localStorage.user || 'Unknown User';

        $scope.$watch('[user]', function () {
            $localStorage.user = $scope.user;
        }, true);

        $scope.login = function () {
            var params = {userName: $scope.username, password: $scope.password};
            if (params.userName && params.password) {
                loginService.userLogin(params).then(function (result) {
                    console.log("login service returns:" + result);
                    $scope.user = result;
                    if (result.has)//to prevent error when result is undefined
                        if (result.username === params.username && result.password === params.password) {
                            $window.location.href = '../dashBoard.html';
                        } else {
                            $scope.incorrect = true;
                            console.log("role is user");
                        }
                }, function () {
                    console.log("error");
                });
            }


            console.log($scope.user);
        }
    });
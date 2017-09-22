angular.module('hrmsLogin')
    .controller('loginController', function ($scope, $localStorage, $sessionStorage, $window, loginService, $http, $httpParamSerializerJQLike) {
        $scope.user = {};
        $scope.user = $localStorage.user || 'Unknown User';

        $scope.$watch('[user]', function () {
            $localStorage.user = $scope.user;
        }, true);

        $scope.login = function () {
            var params = {username: $scope.username, password: $scope.password};
            if (params.username && params.password) {
                loginService.userLogin(params).then(function (result) {
                    console.log("login service returns:" + result);
                    $scope.user = result;
                    if (result.status == "200")//to prevent error when result is undefined
                        $window.location.href = '../dashBoard.html';
                }, function () {
                    $scope.incorrect = true;
                    console.log("error");
                });
            }


            console.log("current user [" + $scope.user.username + "/" + $scope.user + "]");
        }
    });
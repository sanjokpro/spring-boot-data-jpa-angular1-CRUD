angular.module('hrmsLogin')
    .controller('loginController', function ($scope, $window, loginService) {
        $scope.user = {};
        $scope.login = function () {
            var params = {username: $scope.username, password: $scope.password};
            loginService.login(params).then(function (result) {
                console.log("login service returns:" + result);
                $scope.user = result;
                if (result.username === params.username && result.password === params.password) {
                    console.log("Role is admin");
                    $window.location.href = '../dashBoard.html';
                } else {
                    $scope.incorrect = true;
                    console.log("role is user");
                }
            }, function () {
                console.log("error");
            });
            console.log($scope.user);
        }
    });
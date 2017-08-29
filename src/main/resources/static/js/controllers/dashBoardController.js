angular.module("hrmsDashboard")
    .controller("dashBoardController", function ($scope, $localStorage, $window) {
        $scope.user = {};
        $scope.user = $localStorage.user || 'Unknown User';
        console.log($scope.user.userName);
        $scope.logOut = function () {
            $localStorage.$reset();
            $window.location.href = '../index.html';
        }

    });
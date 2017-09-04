angular.module("hrmsDashboard")
    .controller("dashBoardController", function ($scope, $localStorage, $window, dashBoardService, $state, NgTableParams) {
        //_____________________________________________________
        $scope.user = {};
        $scope.user = $localStorage.user || 'Unknown User';
        console.log($scope.user.userName);
        $scope.logOut = function () {
            $localStorage.$reset();
            $window.location.href = '../index.html';
        }
        //___________________________________________________

        $scope.userModel = {}
        $scope.addUser = function () {
            dashBoardService.addUser($scope.userModel).then(function (result) {
                if (result)
                    alert("Operation Successful!")
            }, function () {
                alert("Oops!");
            });

        }
        //____________________________________________________
        $scope.allUser = function () {
            dashBoardService.findAllUser().then(function (receivedData) {
                // $scope.data = [{userName: "usera", firstName: "A", lastName: "B"},
                //     {userName: "userb", firstName: "B", lastName: "C"}];
                $scope.tableParams = new NgTableParams({}, {dataset: receivedData.plain()});
                console.log("SUCCESS");
            }, function () {
                console.log("ERROR");
            });

        };
        //____________________________________________________
        if ($state.$current.data)
            $scope.title = $state.$current.data.title;

    });
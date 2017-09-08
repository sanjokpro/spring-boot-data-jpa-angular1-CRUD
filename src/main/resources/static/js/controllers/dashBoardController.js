angular.module("hrmsDashboard")
    .controller("dashBoardController", function ($scope, $localStorage, $window, dashBoardService, $state, NgTableParams) {

        //____________________________________________________
        $scope.toggleClass = function (targetId, className) {
            className = className || 'is-open';
            $(targetId).toggleClass(className);
        };
        //_____________________________________________________
        $scope.user = {};
        $scope.user = $localStorage.user || 'Unknown User';
        console.log($scope.user.userName);
        $scope.logOut = function () {
            $localStorage.$reset();
            $window.location.href = '../index.html';
        };
        //___________________________________________________
        $scope.removeUser = function (param) {
            dashBoardService.removeUser(param).then(
                function (response) {
                    if (response)
                        var existingList = $scope.allUserList;
                    // $scope.allUserList = [];
                    angular.forEach($scope.allUserList, function (user) {
                        if (user.userId == param.userId) {
                            $scope.allUserList.pop(user);
                        }
                    });
                    console.log("SUCCESS")

                }, function () {
                    console.log("Error!");
                });
        };
        //___________________________________________________

        $scope.userModel = {}
        $scope.addUser = function () {
            dashBoardService.addUser($scope.userModel).then(function (result) {
                if (result)
                    console.log("Operation Successful!")
            }, function () {
                console.log("Oops!");
            });

        };

        //____________________________________________________
        $scope.allUser = function () {
            dashBoardService.findAllUser().then(function (receivedData) {
                $scope.allUserList = receivedData.plain();

                // $scope.tableParams = new NgTableParams({}, {
                //     dataset: receivedData.plain()});
                console.log("SUCCESS");
            }, function () {
                console.log("ERROR");
            });

        };
        //____________________________________________________
        $scope.editUser = function (clickedRowUserObject) {
            for (i = 0; i < $scope.allUserList.length; i++) {
                if ($scope.allUserList[i].userId === clickedRowUserObject.userId) {
                    $scope.allUserList[i].editing = true;
                } else {
                    $scope.allUserList[i].editing = false;
                }
            }
            // angular.forEach($scope.allUserList, function (user, index) {
            //     console.log(index);
            //     if (user.userId == clickedRowUserObject.userId) {
            //         $scope.allUserList[index].editing = true;
            //
            //     }
            // });
        }
        //____________________________________________________
        $scope.updateUser = function (user) {
            dashBoardService.updateUser(user).then(function (response) {
                for (i = 0; i < $scope.allUserList.length; i++) {
                    $scope.allUserList[i].editing = null;
                }
                console.log("user updated!");
            }, function () {
                console.log("unable to update user!");
            });

        }
        //____________________________________________________
        if ($state.$current.data)
            $scope.title = $state.$current.data.title;

    });
angular.module("hrmsDashboard")
    .controller("dashBoardController", function ($scope, $localStorage, $window, dashBoardService, $state, NgTableParams, $uibModal) {
        console.log("**********dashBoardController*******************");

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
                    if (response) {
                        angular.forEach($scope.allUserList.data, function (user, index) {
                            if (user.userId == param.userId) {
                                // delete $scope.allUserList.data[index];
                                //$scope.allUserList.data[index] = undefined;
                                $scope.allUserList.data.splice(index, 1);
                                $scope.msg = "Removed Successfully.";
                                $scope.showMesssageModal('sm');
                            }
                        });
                    } else {
                        $scope.err = "Unable to remove user.";
                    }

                }, function () {
                    console.log(" !  (userId:" + response.userId + ")")
                });
        };
        //___________________________________________________

        $scope.userModel = {}
        $scope.addUser = function () {
            if ($scope.userModel.address && $scope.userModel.password && $scope.userModel.email && $scope.userModel.firstName && $scope.userModel.lastName && $scope.userModel.userName)
                dashBoardService.addUser($scope.userModel).then(function (result) {
                    if (result) {
                        $scope.msg = "User Added Successfully!";
                        $scope.showMesssageModal('sm');
                        $state.go('view');
                    } else {
                        $scope.err = "Unable to add user.";
                    }

                }, function () {
                    console.log("Oops!");
                });
        };

        //____________________________________________________
        $scope.allUser = function () {
            dashBoardService.findAllUser().then(function (receivedData) {
                $scope.allUserList = receivedData;
                $scope.allUserList = new NgTableParams({
                    sorting: {userName: "desc"}
                }, {
                    dataset: $scope.allUserList.plain()
                });

                console.log("data loaded");
            }, function () {
                console.log("data loading error");
            });
        };
        if ($state.current.name === 'view')
            $scope.allUser();
        //____________________________________________________
        $scope.editUser = function (clickedRowUserObject) {
            for (i = 0; i < $scope.allUserList.data.length; i++) {
                if ($scope.allUserList.data[i].userId === clickedRowUserObject.userId) {
                    $scope.allUserList.data[i].editing = true;
                } else {
                    $scope.allUserList.data[i].editing = false;
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
                for (i = 0; i < $scope.allUserList.data.length; i++) {
                    $scope.allUserList.data[i].editing = null;
                }
                $scope.msg = "Updated Successfully.";
                $scope.showMesssageModal('sm');
                ("user updated!");
            }, function () {
                console.log("unable to update user!");
            });

        }
        //____________________________________________________
        if ($state.$current.data)
            $scope.title = $state.$current.data.title;

        /*----------------------UI-bootstrap-Modals----------------------*/
        $scope.showMesssageModal = function (size) {
            var modalInstance = $uibModal.open({
                animation: false,
                ariaLabelledBy: 'modal-header',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'modals/message-box-modal.html',
                controller: 'modalController',
                size: size,
                backdrop: true,
                resolve: {
                    msg: function () {
                        return $scope.msg;
                    }
                }

            }).result.then(function () {
                console.log("result=true");
            }, function (res) {
                console.log(res);
            });
            return modalInstance;
        }

    });

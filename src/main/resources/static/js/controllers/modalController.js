angular.module("hrmsDashboard")
    .controller("modalController", function ($scope, $uibModalInstance, msg) {
        $scope.msg = msg;
        $scope.dismiss = function () {
            $uibModalInstance.dismiss('cancel');
        }
        $scope.close = function () {
            $uibModalInstance.close();
        }
    });
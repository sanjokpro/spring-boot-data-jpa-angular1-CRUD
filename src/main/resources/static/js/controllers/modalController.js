angular.module("hrmsDashboard")
    .controller("modalController", function ($scope, $uibModalInstance) {
        $scope.dismiss = function () {
            $uibModalInstance.dismiss('cancel');
        }
        $scope.close = function () {
            $uibModalInstance.close();
        }
    });
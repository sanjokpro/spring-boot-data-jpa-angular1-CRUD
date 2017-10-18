angular.module("hrmsDashboard")
    .controller("modalController", function ($scope, $uibModalInstance, msg,err) {
        $scope.msg = msg||'';
        $scope.err=err||'';
        $scope.dismiss = function () {
            $uibModalInstance.dismiss('cancel');
        }
        $scope.close = function () {
            $uibModalInstance.close();
        }
    });

angular.module("hrmsLogin")
  .controller("modalController", function ($scope, $uibModalInstance, msg,err) {
    $scope.msg = msg||'';
    $scope.err=err||'';
    $scope.dismiss = function () {
      $uibModalInstance.dismiss('cancel');
    }
    $scope.close = function () {
      $uibModalInstance.close();
    }
  });

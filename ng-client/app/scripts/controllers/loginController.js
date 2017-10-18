angular.module('hrmsLogin')
  .controller('loginController', function ($scope, $localStorage, $sessionStorage, $window, loginService, $http, $httpParamSerializerJQLike, $uibModal) {
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
          if (result.status == "200")//to prevent error when result is undefined
            $scope.getCurrentUser();
          // $window.location.href = '../dashBoard.html';
        }, function (response) {
          if (response.status == "404") {
            $scope.err = "URL not found !";
            $scope.msg = "";
            $scope.showErrorModal('sm');
          }else{$scope.incorrect = true;}

        });
      }
      console.log("current user [" + $scope.user.username + "/" + $scope.user + "]");
    }
    $scope.getCurrentUser = function () {
      loginService.getCurrentUSer().then(
        function (response) {
          $scope.user = response;
          $window.location='../../views/dashBoard.html';
        },
        function () {
          console.log("unable to retrive current user");
        });
    }

    $scope.showErrorModal = function (size) {
      var modalInstance = $uibModal.open({
        animation: false,
        ariaLabelledBy: 'modal-header',
        ariaDescribedBy: 'modal-body',
        templateUrl: '../views/modals/error-modal.html',
        controller: 'modalController',
        size: size,
        backdrop: true,
        resolve: {
          msg: function () {
            return $scope.msg;
          },
          err: function () {
            return $scope.err;
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

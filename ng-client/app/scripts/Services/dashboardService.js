angular.module("hrmsDashboard")
  .factory("dashboardService", function (Restangular, $q) {
    Restangular.setBaseUrl('/api');
    return {

      removeUser:
        function (userIdToDelete) {
          var defer = $q.defer();
          var delUser = Restangular.one('user/remove/' + userIdToDelete.userId);
          delUser.remove().then(function (out) {
            defer.resolve(out);
          }, function (res) {
            defer.reject(res);
          });
          return defer.promise;
        },

      findAllUser:
        function () {
          var defer = $q.defer();
          var newUser = Restangular.all('user/all');
          newUser.get('').then(function (out) {
            defer.resolve(out);
          }, function (res) {
            defer.reject(res);
          });
          return defer.promise;

        },
      addUser:
        function (params) {
          var defer = $q.defer();
          var newUser = Restangular.all('user/add');
          newUser.post(params).then(function (out) {
            defer.resolve(out);
          }, function (res) {
            defer.reject(res);
          });
          return defer.promise;
        },
      updateUser:
        function (userObject) {
          var defer = $q.defer();
          var url = Restangular.all('user/update');
          url.doPUT(userObject).then(function (response) {
            defer.resolve(response);
          }, function (res) {
            defer.reject(res);
          });
          return defer.promise;
        },
      logout: function () {
        var defer = $q.defer();
        var url = Restangular.all('logout');
        url.doPOST().then(function (response) {
          defer.resolve(response);
        }, function (res) {
          defer.reject(res);
        });
        return defer.promise;
      }

    };
    // return object;
  });

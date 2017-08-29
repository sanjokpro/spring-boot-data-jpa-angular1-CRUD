angular.module("hrmsLogin")
    .factory("loginService", function (Restangular, $q) {
        var object = {}
        object.userLogin = function (params) {
            var defer = $q.defer();
            var user = Restangular.all('login');
            user.post(params).then(function (login) {
                defer.resolve(login);
            }, function () {
                defer.reject("Failure");
            });
            return defer.promise;
        };
        return object
    });
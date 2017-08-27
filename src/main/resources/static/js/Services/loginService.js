angular.module("hrmsLogin")
    .factory("loginService", function (Restangular, $q) {
        var object = {}
        object.login = function (params) {
            var def = $q.defer();
            var user = Restangular.one('login');
            user.post(params).then(function (login) {
                def.resolve(login);
                console.log("200:ok")
            }, function () {
                def.reject("Failure");
                console.log("500: err")
            });
        };
        return object
    });
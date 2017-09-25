angular.module("hrmsLogin")
    .factory("loginService", function (Restangular, $q, $http, $httpParamSerializerJQLike) {
        var object = {}
        /*  object.userLogin = function (params) {
             var defer = $q.defer();
             var user = Restangular.all('login');
             user.customPOST($.param(params),{},{},{'Content-Type': "application/x-www-form-urlencoded; charset=UTF-8"}).then(function (login) {
                 defer.resolve(login);
             }, function () {
                 defer.reject("Failure");
             });
             return defer.promise;
         };
         return object*/

        return {
            userLogin: function (requestParam) {
                var defer = $q.defer();
                $http({
                    url: 'login',
                    method: 'POST',
                    data: $httpParamSerializerJQLike(requestParam),
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).then(function (response) {
                    defer.resolve(response);
                }, function () {
                    defer.reject("Failure");
                })
                return defer.promise;
            },


            getCurrentUSer: function () {
                var defer = $q.defer();
                var user = Restangular.all('currentUser');
                user.get('').then(
                    function (response) {
                        defer.resolve(response);
                    },
                    function () {
                        defer.reject("Failure");
                    }
                );
                return defer.promise;
            }

        }

    });
/*
angular.module("hrmsDashboard")
    .factory("dashBoardService", function (Restangular, $q) {
        var object = {}
        object.addUser = function (params) {
            var defer = $q.defer();
            var newUser = Restangular.all('user/add');
            newUser.post(params).then(function (out) {
                defer.resolve(out);
            }, function () {
                defer.reject("Failure");
            });
            return defer.promise;
        };
        return object
    });

angular.module("hrmsDashboard")
    .factory("dashBoardService", function (Restangular, $q) {
        var object = {}
        object.findAllUser = function () {
            var defer = $q.defer();
            var newUser = Restangular.all('user/all');
            newUser.get('').then(function (out) {
                defer.resolve(out);
            }, function () {
                defer.reject("Failure");
            });
            return defer.promise;
        };
        return object
    });


angular.module("hrmsDashboard")
    .factory("removeUser", function (Restangular, $q) {
        var object = {}
        object.removeUser = function (userIdToDelete) {
            var defer = $q.defer();
            var delUser = Restangular.one('user/remove/:id');
            delUser.remove(userIdToDelete).then(function (out) {
                defer.resolve(out);
            }, function () {
                defer.reject("Failure");
            });
            return defer.promise;
        };
        return object
    });
*/
//-----------------------------rewriting-above-code-in-good-way---------------------------------------------------------------

angular.module("hrmsDashboard")
    .factory("dashBoardService", function (Restangular, $q) {
        return {

            removeUser:
                function (userIdToDelete) {
                    var defer = $q.defer();
                    var delUser = Restangular.one('user/remove/' + userIdToDelete.userId);
                    delUser.remove().then(function (out) {
                        defer.resolve(out);
                    }, function () {
                        defer.reject("Failure");
                    });
                    return defer.promise;
                },

            findAllUser:
                function () {
                    var defer = $q.defer();
                    var newUser = Restangular.all('user/all');
                    newUser.get('').then(function (out) {
                        defer.resolve(out);
                    }, function () {
                        defer.reject("Failure");
                    });
                    return defer.promise;

                },
            addUser:
                function (params) {
                    var defer = $q.defer();
                    var newUser = Restangular.all('user/add');
                    newUser.post(params).then(function (out) {
                        defer.resolve(out);
                    }, function () {
                        defer.reject("Failure");
                    });
                    return defer.promise;
                },
            updateUser:
                function (userObject) {
                    var defer = $q.defer();
                    var url = Restangular.all('user/update');
                    url.doPUT(userObject).then(function (response) {
                        defer.resolve(response);
                    }, function () {
                        defer.reject("Failure");
                    });
                    return defer.promise;
                }

        }
        // return object;
    });
angular.module("hrmsDashboard")
    .config(function ($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('user', {
                url: 'user/add',
                templateUrl: 'user-add.html',
                controller: 'dashBoardController',
                data: {title: 'Add User'}
            })
            .state('view', {
                url: 'user/view',
                templateUrl: 'user-list.html',
                controller: 'dashBoardController',
                data: {title: 'View User'}
            })

    });

angular.module("hrmsDashboard")
    .config(function ($stateProvider, $urlRouterProvider) {

        //$urlRouterProvider.otherwise('user');
        $urlRouterProvider.otherwise('/user/view');

        $stateProvider
            .state('user', {
                url: '/user/add',
                templateUrl: 'templates/user-add.html',
                controller: 'dashBoardController',
                data: {title: 'Add User'}
            })
            .state('view', {
                url: '/user/view',
                templateUrl: 'templates/user-list.html',
                controller: 'dashBoardController',
                data: {title: 'View User'}
            });

    });

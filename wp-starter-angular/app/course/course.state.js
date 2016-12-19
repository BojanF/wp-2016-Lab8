/**
 * Created by Bojan on 12/19/2016.
 */
(function (angular) {
  'use strict';

  angular
    .module('wp-angular-starter')
    .config(registerState);


  //registerState.$inject = ['$stateProvider'];

  function registerState($stateProvider) {

    $stateProvider.state('course', {
      url: '/courses',
      templateUrl: 'app/course/course.view.html',
      controller: 'CourseController',
      controllerAs: 'vm'
    });
  }

})(angular);

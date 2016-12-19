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

    $stateProvider.state('association', {
      url: '/associations',
      templateUrl: 'app/association/association.view.html',
      controller: 'AssociationController',
      controllerAs: 'vm'
    });
  }

})(angular);

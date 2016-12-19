/**
 * Created by Bojan on 12/19/2016.
 */
(function (angular) {
  'use strict';

  angular
    .module('wp-angular-starter')
    .controller('AssociationController', AssociationControllerFn);

  AssociationControllerFn.$inject = ['$log', 'AssociationService'];

  /* @ngInject */
  function AssociationControllerFn($log, AssociationService) {
    var vm = this;
    vm.title = 'Association students with courses';
    vm.save = saveAssociation;
    vm.clear = clear;
    /*vm.edit = edit;
    vm.remove = remove;*/
    vm.associationEntity = {};
    vm.courseEntities = [];
    vm.studentEntities = [];
    vm.saveOkMsg = null;
    vm.saveErrMsg = null;

    loadCourses();
    loadStudents();


    function loadCourses() {
      AssociationService.getAllCourses().then(function (data) {
        vm.courseEntities = data;
      });
    }

    function loadStudents() {
      AssociationService.getAllStudents().then(function (data) {
        vm.studentEntities = data;
      });
    }

    function saveAssociation() {
      $log.debug("SE POVIKA");
      $log.debug(vm.associationEntity.courseEntity);
      $log.debug(vm.associationEntity.studentEntity);
      vm.saveOkMsg = null;
      vm.saveErrMsg = null;

      var promise = AssociationService.save(vm.associationEntity);
      promise.then(successCallback, errorCallback);
      function successCallback(data) {
        //loadStudents();
        vm.saveOkMsg = "Student with id " + data.id + " is saved";
        clear();
      }

      function errorCallback(data) {
        vm.saveErrMsg = "Saving error occurred: " + data.message;
      }
    }

    function clear() {
      $log.debug("HIHI");
      vm.associationEntity = {};
    }

    /*function edit(studentEntity) {
      vm.studentEntity = {};
      angular.extend(vm.studentEntity, studentEntity);
    }

    function remove(studentEntity) {
      StudentService
        .remove(studentEntity)
        .then(function () {
          loadStudents();
        });
    }*/
  }

})(angular);

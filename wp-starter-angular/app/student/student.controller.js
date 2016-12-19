/**
 * Created by Bojan on 10/17/2016.
 */
(function (angular) {
  'use strict';

  angular
    .module('wp-angular-starter')
    .controller('StudentController', StudentController);

  StudentController.$inject = ['$log', 'StudentService'];

  /* @ngInject */
  function StudentController($log, StudentService) {
    var vm = this;
    vm.title = 'Student';
    vm.save = saveStudent;
    vm.clear = clear;
    vm.edit = edit;
    vm.remove = remove;
    vm.studentEntity = {};
    vm.studentEntities = [];
    vm.saveOkMsg = null;
    vm.saveErrMsg = null;

    loadStudents();



    function loadStudents() {
      StudentService.getAll().then(function (data) {
        vm.studentEntities = data;
      });
    }

    function saveStudent() {
      $log.debug("SE POVIKA");
      vm.saveOkMsg = null;
      vm.saveErrMsg = null;

      var promise = StudentService.save(vm.studentEntity);
      promise.then(successCallback, errorCallback);
      function successCallback(data) {
        loadStudents();
        vm.saveOkMsg = "Student with id " + data.id + " is saved";
        clear();
      }

      function errorCallback(data) {
        vm.saveErrMsg = "Saving error occurred: " + data.message;
      }
    }

    function clear() {
      $log.debug("HIHI");
      vm.studentEntity = {};
    }

    function edit(studentEntity) {
      vm.studentEntity = {};
      angular.extend(vm.studentEntity, studentEntity);
    }

    function remove(studentEntity) {
      StudentService
        .remove(studentEntity)
        .then(function () {
          loadStudents();
        });
    }
  }

})(angular);


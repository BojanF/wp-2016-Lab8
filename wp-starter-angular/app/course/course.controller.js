/**
 * Created by Bojan on 12/19/2016.
 */

(function (angular) {
  'use strict';

  angular
    .module('wp-angular-starter')
    .controller('CourseController', CourseControllerFn);

  CourseControllerFn.$inject = ['$log', 'CourseService'];

  /* @ngInject */
  function CourseControllerFn($log, CourseService) {
    var vm = this;
    vm.title = 'Course';
    vm.save = save;
    vm.clear = clear;
    vm.edit = edit;
    vm.remove = remove;
    vm.entity = {};
    vm.courseEntities = [];
    vm.saveOkMsg = null;
    vm.saveErrMsg = null;
    vm.availableSizes = [];
    loadCourses();

    function loadCourses() {
      CourseService.getAll().then(function (data) {
        vm.courseEntities = data;
        vm.availableSizes = data;
      });
    }

    function save() {
      vm.saveOkMsg = null;
      vm.saveErrMsg = null;

      var promise = CourseService.save(vm.entity);

      promise.then(successCallback, errorCallback);
      function successCallback(data) {
        $log.debug(vm.entity.name);
        loadCourses();
        vm.saveOkMsg = "Group with id " + data.id + " is saved";
        clear();
      }

      function errorCallback(data) {
        vm.saveErrMsg = "Saving error occurred: " + data.message;
      }
    }

    function clear() {
      vm.entity = {};
    }

    function edit(entity) {
      vm.entity = {};
      angular.extend(vm.entity, entity);
    }

    function remove(entity) {
      CourseService
        .remove(entity)
        .then(function () {
          loadCourses();
        });
    }
  }

})(angular);



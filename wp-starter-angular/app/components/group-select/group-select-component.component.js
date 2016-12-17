/**
 * Created by Bojan on 11/14/2016.
 */


(function () {
  'use strict';

  angular
    .module('wp-angular-starter')
    .component('groupSelect', {
      templateUrl: 'app/components/group-select/group-select-component.view.html',
      bindings: {
        gsModel: "="
      },
      controller: GroupSelectComponent

    });
  GroupSelectComponent.$inject = ['$attrs', 'GroupService'];
  function GroupSelectComponent($attrs, GroupService){
    var vm = this;
    vm.LabGroups = [];
    GroupService.getAll().then(function(data){
      vm.LabGroups = data;
    });
    //this.gsModel = $attrs.gsModel;

  };



})();



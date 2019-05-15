/**
 * Created by DELL on 2019/4/25.
 */

var search_type = "none";
var search_keyWord = "";
var selectID;

$(function() {
    optionAction();
    searchAction();
    customerListInit();
    bootstrapValidatorInit();
    // addCustomerAction();
    // editCustomerAction();
    deleteCustomerAction();
    tableRefresh();
    addGoodsAction();
    bootstrapValidatorInitGoods();
})

// 下拉框選擇動作
function optionAction() {
    $(".dropOption").click(function() {
        var type = $(this).text();
        $("#search_input").val("");
        if (type == "所有") {
            $("#search_input").attr("readOnly", "true");
            search_type = "searchAll";
        } else if (type == "款号") {
            $("#search_input").removeAttr("readOnly");
            search_type = "searchByKuanhao";
        } else if (type == "客户名称") {
            $("#search_input").removeAttr("readOnly");
            search_type = "searchByKehu";
        } else {
            $("#search_input").removeAttr("readOnly");
        }
        $("#search_type").text(type);
        $("#search_input").attr("placeholder", type);
    })
}

// 搜索动作
function searchAction() {
    $('#search_button').click(function() {
        search_keyWord = $('#search_input').val();
        tableRefresh();
    })
}

// 分页查询参数
function queryParams(params) {
    var temp = {
        limit : params.limit,
        offset : params.offset,
        searchType : search_type,
        keyWord : search_keyWord,
        sortOrder: params.order,//排序
        sortName:params.sort//排序字段
    }
    return temp;
}

// 表格初始化
function customerListInit() {
    $('#customerList').bootstrapTable({
                columns : [
                    {
                        field : 'id',
                        title : 'id',
                        width : '100px',
                        sortable: true
                    },
                    {
                        field : 'kuanhao',
                        title : '款号',
                        width : '100px'
                        //sortable: true
                    },
                    {
                        field : 'kehu',
                        title : '客户名称'
                    },
                    {
                        field : 'yanse',
                        title : '颜色',
                        sortable: true
                    },
                    {
                        field : 'chriqi',
                        title : '出货日期',
                        visible : false
                    },
                    {
                        field : 'xs',
                        title : 'XS/34'
                    },
                    {
                        field : 's',
                        title : 'S/36'
                    },
                    {
                        field : 'm',
                        title : 'M/38'
                    },
                    {
                        field : 'l',
                        title : 'L/40'
                    },
                    {
                        field : 'xl',
                        title : 'XL/42'
                    },
                    {
                        field : 'xxl',
                        title : 'XXL/44'
                    },
                    {
                        field : 'xxxl',
                        title : 'XXXL/46'
                    },
                    {
                        field : 'ywshuliang',
                        title : '业务数量'
                    },
                    {
                        field : 'operation',
                        title : '操作',
                        formatter : function(value, row, index) {
                            // var s = '<button class="btn btn-info btn-sm edit"><span>编辑</span></button>';
                            var d = '<button class="btn btn-danger btn-sm delete"><span>删除</span></button>';
                            // var b = '<button id="showme" class="btn btn-info btn-sm search"><span>查看</span></button>';
                            var fun = '';
                            return  d ;
                        },
                        events : {
                            // 操作列中编辑按钮的动作
                            // 'click .edit' : function(e, value,
                            //                          row, index) {
                            //     selectID = row.kuanhao;
                            //     rowEditOperation(row);
                            // },
                            'click .delete' : function(e,
                                                       value, row, index) {
                                selectID = row.id;
                                $('#deleteWarning_modal').modal(
                                    'show');
                            }
                            // 'click #showme': function (e, value,
                            //                          row, index) {
                            //     selectID = row.kuanhao;
                            //     search_keyWord = selectID;
                            //     detailTableRefresh();
                            //     rowShowOperation(row);
                            // }
                        }
                    }],
                url : 'all',
                onLoadSuccess:function(json){
                    $("#customerList").bootstrapTable('load',json);
                },
                onLoadError:function(status){
                    handleAjaxError(status);
                },
                locale : 'zh-CN',
                method : 'GET',
                sortName : 'id',
                sortOrder : 'desc',
                queryParams : queryParams,
                sidePagination : "server",
                contentType: "application/x-www-form-urlencoded",
                uniqueId: "id",
                pagination : true,
                pageNumber : 1,
                pageSize : 5,
                pageList : 10,
                clickToSelect : true
    });
}

// 查看操作
// function rowShowOperation(row) {
//     $('#show_modal').modal("show");
//     $('#show_modal').modal({backdrop: 'static', keyboard: false});
//     search_type = "findByKuanhao";
//     $('#close_showme').click(function(){
//         $('#show_modal').modal("hide");
//         search_type = "none";});
//     $('#goodsdetail').bootstrapTable(
//         {
//             columns : [
//                 {
//                     field : 'kuanhao',
//                     title : '款号'
//                     //sortable: true
//                 },
//                 {
//                     field : 'yanse',
//                     title : '颜色'
//                 },
//                 {
//                     field : 'xs',
//                     title : 'XS/34'
//                 },
//                 {
//                     field : 's',
//                     title : 'S/36'
//                 },
//                 {
//                     field : 'm',
//                     title : 'M/38'
//                 },
//                 {
//                     field : 'l',
//                     title : 'L/40'
//                 },
//                 {
//                     field : 'xl',
//                     title : 'XL/42'
//                 },
//                 {
//                     field : 'xxl',
//                     title : 'XXL/44'
//                 },
//                 {
//                     field : 'xxxl',
//                     title : 'XXXL/46'
//                 }],
//             locale : 'zh-CN',
//             url : 'findByKuanhao',
//             method : 'GET',
//             queryParams : queryParams,
//             sidePagination : "server",
//             contentType: "application/x-www-form-urlencoded",
//             dataType : 'json',
//             pagination : true,
//             pageNumber : 1,
//             pageSize : 5,
//             pageList : [ 5, 10, 25, 50, 100 ],
//             clickToSelect : true
//         }
//     );
//     // $('#sumGoods').bootstrapTable(
    //     {
    //         columns : [
    //             {
    //                 field : 'kuanhao',
    //                 title : '款号'
    //             },
    //             {
    //                 field : 'yanse',
    //                 title : '颜色'
    //             },
    //             {
    //                 field : 'xs',
    //                 title : 'XS/34'
    //             },
    //             {
    //                 field : 's',
    //                 title : 'S/36'
    //             },
    //             {
    //                 field : 'm',
    //                 title : 'M/38'
    //             },
    //             {
    //                 field : 'l',
    //                 title : 'L/40'
    //             },
    //             {
    //                 field : 'xl',
    //                 title : 'XL/42'
    //             },
    //             {
    //                 field : 'xxl',
    //                 title : 'XXL/44'
    //             },
    //             {
    //                 field : 'xxxl',
    //                 title : 'XXXL/46'
    //             }],
    //         locale : 'zh-CN',
    //         url : 'selectBykuanhaoyanse',
    //         method : 'GET',
    //         sidePagination : "server",
    //         queryParams : queryParams,
    //         contentType: "application/x-www-form-urlencoded",
    //         dataType : 'json',
    //         pagination : true,
    //         pageNumber : 1,
    //         pageSize : 5,
    //         pageList : [ 5, 10, 25, 50, 100 ],
    //         clickToSelect : true
    //     }
    // );

// }

// 添加货物信息
function addGoodsAction() {
    $('#add_goods').click(function() {
        dateNow();
        $('#add_goods_modal').modal("show");
    });
    $('#add_goods_modal_submit').click(function() {
        var msg = "操作失败";//非submit按钮点击后进行验证，如果是submit则无需此句直接验证
        $("#goods_form").bootstrapValidator('validate');//提交验证
        if ($("#goods_form").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
            var data = {
                kuanhao : $('#kuanhaogoods').val(),
                yanse : $('#yanse').val(),
                kehu : $('#kehu').val(),
                mianliao : $('#mianliao').val(),
                chriqi : $('#chriqi').val(),
                xs : $('#xs').val(),
                s : $('#s').val(),
                m : $('#m').val(),
                l :$('#l').val(),
                xl : $('#xl').val(),
                xxl : $('#xxl').val(),
                xxxl : $('#xxxl').val()

            }
            // ajax
            $.ajax({
                type : "POST",
                url : "insertYewu",
                dataType : "json",
                contentType : "application/json",
                data : JSON.stringify(data),
                success : function(response) {
                    $('#add_goods_modal').modal("hide");

                    if (response.result == "success") {
                        msg = "操作成功";

                    } else if (response.status != "success") {
                        msg = "操作失败";

                    }
                    alert(msg);
                    tableRefresh();
                    // reset
                    $('#kuanhaogoods').val("");
                    $('#yanse').val("");
                    $('#kehu').val("");
                    $('#mianliao').val("");
                    $('#xs').val("0");
                    $('#s').val("0");
                    $('#m').val("0");
                    $('#l').val("0");
                    $('#xl').val("0");
                    $('#xxl').val("0");
                    $('#xxxl').val("0");
                    dateNow();
                    $('#goods_form').data("bootstrapValidator").resetForm();
                },
                error : function(xhr, textStatus, errorThrow) {
                    $('#add_goods_modal').modal("hide");
                    // handler error
                    handleAjaxError(xhr.status);
                }
            });
        }
    });
}


// 表格刷新
function tableRefresh() {
    $('#customerList').bootstrapTable('refresh', {
        query : {}
    });
}

// 行编辑操作
// function rowEditOperation(row) {
//     $('#edit_modal').modal("show");
//
//     // load info
//     $('#customer_form_edit').bootstrapValidator("resetForm", true);
//     $('#kuanhao_edit').val(row.kuanhao)
//     $('#kehu_edit').val(row.kehu);
//     $('#ywbshuliang_edit').val(row.ywbshuliang);
//     $('#mianliao_edit').val(row.mianliao);
//     $('#chriqi_edit').val(row.chriqi);
// }

//详情验证
function bootstrapValidatorInitGoods() {
    $("#goods_form").bootstrapValidator({
        live: 'enabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
        excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的
        submitButtons: '#add_goods_modal_submit',
        message: 'This is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        excluded: [':disabled'],
        fields: {
            kuanhaogoods: {
                validators: {
                    notEmpty: {
                        message: '款号不能为空'
                    }
                }
            },
            yanse: {
                validators: {
                    notEmpty: {
                        message: '颜色不能为空'
                    }
                }
            },
            kehu : {
                validators : {
                    notEmpty : {
                        message : '客户名字不能为空'
                    }
                }
            },
            mianliao : {
                validators : {
                    notEmpty : {
                        message : '面料不能为空'
                    }
                }
            },
            chriqi : {
                validators : {
                    notEmpty : {
                        message : '出货日期不能为空'
                    },
                    regexp : {
                        regexp : '^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$',
                        message : '日期的格式不正确，正确格式为：2014-01-01'
                    }
                }
            },
            xs : {
                validators : {
                    notEmpty: {
                        message:'尺码不能为空，如没有请输入0'
                    },
                    regexp : {
                        regexp : '^[0-9]*$',
                        message : '只能输入0-9的整数，不能输入特殊字符'
                    }
                }
            },
            s : {
                validators : {
                    notEmpty: {
                        message:'尺码不能为空，如没有请输入0'
                    },
                    regexp : {
                        regexp : '^[0-9]*$',
                        message : '只能输入0-9的整数，不能输入特殊字符'
                    }
                }
            },
            m : {
                validators : {
                    notEmpty: {
                        message:'尺码不能为空，如没有请输入0'
                    },
                    regexp : {
                        regexp : '^[0-9]*$',
                        message : '只能输入0-9的整数，不能输入特殊字符'
                    }
                }
            },
            l : {
                validators : {
                    notEmpty: {
                        message:'尺码不能为空，如没有请输入0'
                    },
                    regexp : {
                        regexp : '^[0-9]*$',
                        message : '只能输入0-9的整数，不能输入特殊字符'
                    }
                }
            },
            xl : {
                validators : {
                    notEmpty: {
                        message:'尺码不能为空，如没有请输入0'
                    },
                    regexp : {
                        regexp : '^[0-9]*$',
                        message : '只能输入0-9的整数，不能输入特殊字符'
                    }
                }
            },
            xxl : {
                validators : {
                    notEmpty: {
                        message:'尺码不能为空，如没有请输入0'
                    },
                    regexp : {
                        regexp : '^[0-9]*$',
                        message : '只能输入0-9的整数，不能输入特殊字符'
                    }
                }
            },
            xxxl : {
                validators : {
                    notEmpty: {
                        message:'尺码不能为空，如没有请输入0'
                    },
                    regexp : {
                        regexp : '^[0-9]*$',
                        message : '只能输入0-9的整数，不能输入特殊字符'
                    }
                }
            }
        }
    })
}

// 添加供应商模态框数据校验
// function bootstrapValidatorInit() {
//     $("#customer_form,#customer_form_edit").bootstrapValidator({
//         live: 'enabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
//         excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的
//         submitButtons: ['#edit_modal_submit','#add_modal_submit'],
//         message : 'This is not valid',
//         feedbackIcons : {
//             valid : 'glyphicon glyphicon-ok',
//             invalid : 'glyphicon glyphicon-remove',
//             validating : 'glyphicon glyphicon-refresh'
//         },
//         excluded : [ ':disabled' ],
//         fields : {
//             kuanhao: {
//                 validators : {
//                     notEmpty : {
//                         message : '款号不能为空'
//                     }
//                 }
//             },
//             kehu : {
//                 validators : {
//                     notEmpty : {
//                         message : '客户名字不能为空'
//                     }
//                 }
//             },
//             ywbshuliang : {
//                 validators : {
//                     notEmpty : {
//                         message : '业务部数量不能为空'
//                     }
//                 }
//             },
//             mianliao : {
//                 validators : {
//                     notEmpty : {
//                         message : '面料不能为空'
//                     }
//                 }
//             },
//             chriqi : {
//                 validators : {
//                     notEmpty : {
//                         message : '出货日期不能为空'
//                     },
//                     regexp : {
//                         regexp : '^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$',
//                         message : '日期的格式不正确，正确格式为：2014-01-01'
//                     }
//                 }
//             }
//         }
//     })
// }

// function detailTableRefresh() {
//     $('#goodsdetail').bootstrapTable('refresh', {
//         query : {}
//     });
//     // $('#sumGoods').bootstrapTable('refresh', {
//     //     query : {}
//     // });
// }

// 编辑客户信息
// function editCustomerAction() {
//     $('#edit_modal_submit').click(
//         function() {
//             var msg = "业务信息更新失败";
//             $('#customer_form_edit').data('bootstrapValidator')
//                 .validate();
//             if (!$('#customer_form_edit').data('bootstrapValidator')
//                     .isValid()) {
//                 return;
//             }
//
//             var data = {
//                 kuanhao : selectID,
//                 kehu : $('#kehu_edit').val(),
//                 ywbshuliang : $('#ywbshuliang_edit').val(),
//                 mianliao : $('#mianliao_edit').val(),
//                 chriqi : $('#chriqi_edit').val()
//             }
//
//             // ajax
//             $.ajax({
//                 type : "POST",
//                 url : 'update',
//                 dataType : "json",
//                 contentType : "application/json",
//                 data : JSON.stringify(data),
//                 success : function(response) {
//                     $('#edit_modal').modal("hide");
//                     var type;
//
//                     var append = ''
//                     if (response.result == "success") {
//                         type = "success";
//                         msg = "业务信息更新成功";
//                         alert(msg);
//                     } else if (response.result != "success") {
//                         type = "error";
//                         msg = "业务信息更新失败";
//                         alert(msg);
//                     }
//                     showMsg(type, msg, append);
//                     tableRefresh();
//                 },
//                 error : function(xhr, textStatus, errorThrown) {
//                     $('#edit_modal').modal("hide");
//                     tableRefresh();
//                     // 处理错误
//                     handleAjaxError(xhr.status)
//                 }
//             });
//         });
// }

// 刪除客户信息
function deleteCustomerAction(){
    $('#delete_confirm').click(function(){
        var msg = "业务信息删除失败";
        var data = {
            id : selectID
        }

        // ajax
        $.ajax({
            type : "GET",
            url : "delete",
            dataType : "json",
            contentType : "application/json",
            data : data,
            success : function(response){
                $('#deleteWarning_modal').modal("hide");
                if(response.result == "success"){

                    msg = "业务信息删除成功";

                }else{

                    msg = "业务信息删除失败";

                }
                alert(msg);
                tableRefresh();
            },error : function(xhr, textStatus, errorThrown){
                $('#deleteWarning_modal').modal("hide");
                // handler error
                handleAjaxError(xhr.status)
            }
        })

        $('#deleteWarning_modal').modal('hide');
    })
}

//获取当前日期
function dateNow() {
    var date = new Date();
    var year = date.getFullYear(); //获取年
    var month = date.getMonth()+1;//获取月
    var day = date.getDate(); //获取当日
    if(month<10){
        month = "0"+ month;
    }
    if(day<10){
        day = "0" + day;
    }
    var time = year+"-"+month+"-"+day; //组合时间   alert("当前日期："+time);
    $('#chriqi').val(time);
}

// 添加客户信息
// function addCustomerAction() {
//     $('#add_customer').click(function() {
//         $('#add_modal').modal("show");
//     });
//     $('#add_modal_submit').click(function() {
//         var msg = "业务添加失败";//非submit按钮点击后进行验证，如果是submit则无需此句直接验证
//         $("#customer_form").bootstrapValidator('validate');//提交验证
//         if ($("#customer_form").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
//              var data = {
//                 kuanhao : $('#kuanhao').val(),
//                 kehu : $('#kehu').val(),
//                 ywbshuliang : $('#ywbshuliang').val(),
//                 mianliao : $('#mianliao').val(),
//                 chriqi : $('#chriqi').val()
//             }
//             // ajax
//             $.ajax({
//                 type : "POST",
//                 url : "insert",
//                 dataType : "json",
//                 contentType : "application/json",
//                 data : JSON.stringify(data),
//                 success : function(response) {
//                     $('#add_modal').modal("hide");
//
//                     if (response.result == "success") {
//                         msg = "业务添加成功";
//                         alert(msg);
//                     } else if (response.result != "success") {
//                         msg = "业务添加失败";
//                         alert(msg);
//                     }
//
//                     // reset
//                     $('#kuanhao').val("");
//                     $('#kehu').val("");
//                     $('#ywbshuliang').val("0");
//                     $('#mianliao').val("");
//                     $('#chriqi').val("2019-05-08");
//                     $('#customer_form').bootstrapValidator("resetForm", true);
//                     tableRefresh();
//                 },
//                 error : function(xhr, textStatus, errorThrown) {
//                     $('#add_modal').modal("hide");
//                     tableRefresh();
//                     alert(msg);
//                     // handler error
//                     handleAjaxError(xhr.status);
//                 }
//             });
//         }
//     });
// }

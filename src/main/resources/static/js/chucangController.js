/**
 * Created by DELL on 2019/4/25.
 */
var search_type_goods = "searchAll";
var search_keyWord = "";

$(function() {
    optionAction();
    searchAction();
    goodsListInit();
    bootstrapValidatorInit();
    addGoodsAction();
})

// 下拉框選擇動作
function optionAction() {
    $(".dropOption").click(function() {
        var type = "查询款号";
        $("#search_input").val("");
        if (type == "查询款号") {
            $("#search_input").attr("readOnly", "true");
            search_type_goods = "searchByKuanhao";
        }  else {
            $("#search_input").removeAttr("readOnly");
        }

        $("#search_type").text(type);
        $("#search_input").attr("placeholder", type);
    })
}

// 搜索动作
function searchAction() {
    $('#search_button').click(function() {
        search_type_goods = "searchByKuanhao";
        search_keyWord = $('#search_input').val();
        tableRefresh();
    })
}

// 分页查询参数
function queryParams(params) {
    var temp = {
        limit : params.limit,
        offset : params.offset,
        searchType : search_type_goods,
        keyWord : search_keyWord
    }
    return temp;
}

// 表格初始化
function goodsListInit() {
    $('#goodsList')
        .bootstrapTable(
            {
                columns : [
                    {
                        field : 'kuanhao',
                        title : '款号',
                        width : '75px'
                        //sortable: true
                    },
                    {
                        field : 'yanse',
                        title : '颜色',
                        width : '75px'
                    },
                    {
                        field : 'ccriqi',
                        title : '出仓日期',
                        width : '100px'
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
                        field : 'beizhu',
                        title : '备注'
                    }],
                url : 'all',
                onLoadSuccess:function(json){
                    $("#goodsList").bootstrapTable('load',json);
                },
                onLoadError:function(status){
                    handleAjaxError(status);
                },
                method : 'GET',
                locale : 'zh-CN',
                queryParams : queryParams,
                sidePagination : "server",
                contentType: "application/x-www-form-urlencoded",
                dataType : 'json',
                pagination : true,
                pageNumber : 1,
                pageSize : 5,
                pageList : [ 5, 10, 25, 50, 100 ],
                clickToSelect : true
            });
}

// 表格刷新
function tableRefresh() {
    $('#goodsList').bootstrapTable('refresh', {
        query : {}
    });
}

// function detailTableRefresh() {
//     $('#goodsdetail').bootstrapTable('refresh', {
//         query : {}
//     });
//     // $('#sumGoods').bootstrapTable('refresh', {
//     //     query : {}
//     // });
// }


// 查看操作
// function rowEditOperation(row) {
//     $('#edit_modal').modal("show");
//     $('#edit_modal').modal({backdrop: 'static', keyboard: false});
//     search_type_goods = "findByKuanhao";
//     $('#edit_modal_submit').click(function(){
//         $('#edit_modal').modal("hide")});
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
//                     field : 'ccriqi',
//                     title : '出仓日期'
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
//             url : 'findByKuanhao',
//             locale : 'zh-CN',
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
    // $('#sumGoods').bootstrapTable(
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
//
// }

// 添加供应商模态框数据校验
function bootstrapValidatorInit() {
    $("#goods_form").bootstrapValidator({
        live: 'enabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
        excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的
        submitButtons: '#add_modal_submit',
        message: 'This is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        excluded: [':disabled'],
        fields: {
            kuanhao: {
                validators: {
                    notEmpty: {
                        message: '款号不能为空'
                    }
                }
            },
            ccriqi: {
                validators: {
                    notEmpty: {
                        message: '日期不能为空'
                    },
                    regexp : {
                        regexp : '^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$',
                        message : '日期的格式不正确，正确格式为：2014-01-01'
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


// 添加货物信息
function addGoodsAction() {
    $('#add_goods').click(function() {
        $('#add_modal').modal("show");
    });
    $('#add_modal_submit').click(function() {
        var msg = "操作失败";//非submit按钮点击后进行验证，如果是submit则无需此句直接验证
        $("#goods_form").bootstrapValidator('validate');//提交验证
        if ($("#goods_form").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
            var data = {
                kuanhao: $('#kuanhao').val(),
                ccriqi: $('#ccriqi').val(),
                yanse: $('#yanse').val(),
                xs: $('#xs').val(),
                s: $('#s').val(),
                m: $('#m').val(),
                l: $('#l').val(),
                xl: $('#xl').val(),
                xxl: $('#xxl').val(),
                xxxl: $('#xxxl').val(),
                beizhu: $('#beizhu').val()

            }
            // ajax
            $.ajax({
                type: "POST",
                url: "insert",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function (response) {
                    $('#add_modal').modal("hide");
                    var type;
                    var append = '';
                    if (response.result == "success") {
                        type = "success";
                        msg = "操作成功";
                        alert(msg);
                    } else if (response.result != "success") {
                        type = "error";
                        alert(msg);
                        msg = response.result;
                    } else {
                        msg = "操作失败";
                        alert(msg);
                    }
                    showMsg(type, msg, append);
                    tableRefresh();

                    // reset
                    $('#kuanhao').val();
                    $('#ccriqi').val("2019-05-08");
                    $('#yanse').val();
                    $('#xs').val("0");
                    $('#s').val("0");
                    $('#m').val("0");
                    $('#l').val("0");
                    $('#xl').val("0");
                    $('#xxl').val("0");
                    $('#xxxl').val("0");
                    $('#beizhu').val();
                    $('#goods_form').bootstrapValidator("resetForm", true);
                    tableRefresh();
                },
                error: function (xhr, textStatus, errorThrow) {
                    $('#add_modal').modal("hide");
                    tableRefresh();
                    // handler error
                    handleAjaxError(xhr.status);
                }
            });
        }
    });

}

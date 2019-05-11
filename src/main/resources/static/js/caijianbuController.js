/**
 * Created by DELL on 2019/4/25.
 */
var search_type_goods = "searchAll";
var search_keyWord = "";
var selectID;
var data;

$(function() {
    optionAction();
    searchAction();
    goodsListInit();
    bootstrapValidatorInit();
    addNew();
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
    $('#cjriqi').val(time);
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
                        field : 'id',
                        title : 'ID'
                        //sortable: true
                    },
                    {
                        field : 'kuanhao',
                        title : '款号',
                        width : "250px",
                        halign :"center",
                        align : "center"
                    },
                    {
                        field : 'yanse',
                        title : '颜色',
                        width : "100px",
                        halign :"center",
                        align : "center"
                    },
                    {
                        field : 'ywbshuliang',
                        title : '业务数量',
                        width : "100px",
                        halign :"center",
                        align : "center"
                    },
                    // {
                    //     field : 'cjbshuliang',
                    //     title : '裁剪部数量'
                    // },
                    {
                        field : 'operation',
                        title : '操作',
                        halign :"center",
                        align : "center",
                        formatter : function(value, row, index) {
                            var s = '<button class="btn btn-info" id="moredetail" type="button" style="padding-left: 40px;padding-right: 40px"><span>查看详情</span></button>';
                            var sd = '<button class="btn btn-primary" id="ywLinkcj" type="button" style="margin-left: 20px;padding-left: 20px;padding-right: 20px"><span>比较业务部数量</span></button>';
                            var de = '<button class="btn btn-success" id="myModalLabel" type="button" style="margin-left: 20px;padding-left: 40px;padding-right: 40px"><span>添加详情</span></button>';
                            // var d = '<button class="btn btn-danger btn-sm delete"><span>删除</span></button>';
                            // var fun = '';
                            return s + ' ' + sd + ' ' + de  ;
                        },
                        events : {
                            // 操作列中编辑按钮的动作
                            'click #moredetail': function (e, value,
                                                     row, index) {
                                selectID = row.id;
                                search_keyWord = selectID;
                                search_type_goods = "findByKuanhaoYanse";
                                showYeWu(row);
                                infotableRefresh();
                            },
                            'click #ywLinkcj': function (e, value,
                                                     row, index) {
                                selectID = row.id;
                                search_keyWord = selectID;
                                search_type_goods = "findByKuanhao";
                                rowEditOperation(row);
                                detailTableRefresh();
                            },
                            'click #myModalLabel': function (e, value,
                                                     row, index) {
                                selectID = row.kuanhao;
                                search_keyWord = selectID;
                                addGoodsAction(row);
                            }
                            // 'click .delete': function (e, value,
                            //                          row, index) {
                            //     selectID = row.kuanhao;
                            //     search_keyWord = selectID;
                            //     detailTableRefresh();
                            //     rowEditOperation(row);
                            // }
                        }
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

function showYeWu(row) {
    $('#show_modal').modal("show");
    $('#show_modal').modal({backdrop: 'static', keyboard: false});
    $('#show_modal_submit').click(function(){
        $('#show_modal').modal("hide");
        search_type_goods = "searchAll";
        infotableRefresh();
        });
    $('#showdetail').bootstrapTable({
                columns : [
                    {
                        field : 'kuanhao',
                        title : '款号',
                        width : '250px',
                        halign :"center",
                        align : "center"
                    },
                    {
                        field : 'yanse',
                        title : '颜色',
                        width : "100px",
                        halign :"center",
                        align : "center"
                    },
                    {
                        field : 'cjriqi',
                        title : '裁剪日期',
                        width : '150px',
                        halign :"center",
                        align : "center"
                    },
                    {
                        field : 'xs',
                        title : '裁剪XS/34',
                        halign :"center",
                        align : "center",
                        cellStyle:function(value,row,index) {
                            return {
                                css: {
                                    "font-weight":"bold"
                                }
                            }
                        }
                    },
                    {
                        field : 's',
                        title : '裁剪S/36',
                        halign :"center",
                        align : "center",
                        cellStyle:function(value,row,index) {
                            return {
                                css: {
                                    "font-weight":"bold"
                                }
                            }
                        }
                    },
                    {
                        field : 'm',
                        title : '裁剪M/38',
                        halign :"center",
                        align : "center",
                        cellStyle:function(value,row,index) {
                            return {
                                css: {
                                    "font-weight":"bold"
                                }
                            }
                        }
                    },
                    {
                        field : 'l',
                        title : '裁剪L/40',
                        halign :"center",
                        align : "center",
                        cellStyle:function(value,row,index) {
                            return {
                                css: {
                                    "font-weight":"bold"
                                }
                            }
                        }
                    },
                    {
                        field : 'xl',
                        title : '裁剪XL/42',
                        halign :"center",
                        align : "center",
                        cellStyle:function(value,row,index) {
                            return {
                                css: {
                                    "font-weight":"bold"
                                }
                            }
                        }
                    },
                    {
                        field : 'xxl',
                        title : '裁剪XXL/44',
                        halign :"center",
                        align : "center",
                        cellStyle:function(value,row,index) {
                            return {
                                css: {
                                    "font-weight":"bold"
                                }
                            }
                        }
                    },
                    {
                        field : 'xxxl',
                        title : '裁剪XXXL/46',
                        halign :"center",
                        align : "center",
                        cellStyle:function(value,row,index) {
                            return {
                                css: {
                                    "font-weight":"bold"
                                }
                            }
                        }
                    }],
        onLoadSuccess:function(json){
            $("#showdetail").bootstrapTable('load',json);
        },
        onLoadError:function(status){
            handleAjaxError(status);
        },
                locale : 'zh-CN',
                url : 'all',
                method : 'GET',
                queryParams : queryParams,
                sidePagination : "server",
                contentType: "application/x-www-form-urlencoded; charset=UTF-8 ",
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

function infotableRefresh() {
    $('#showdetail').bootstrapTable('refresh', {
        query : {}
    });
}

function detailTableRefresh() {
    $('#goodsdetail').bootstrapTable('refresh', {
        query : {}
    });
    // $('#sumGoods').bootstrapTable('refresh', {
    //     query : {}
    // });
}


// 查看操作
function rowEditOperation(row) {
    $('#edit_modal').modal("show");
    $('#edit_modal').modal({backdrop: 'static', keyboard: false});
    $('#edit_modal_submit').click(function(){
        $('#edit_modal').modal("hide");
        search_type_goods = "searchAll";
        tableRefresh();});
    $('#goodsdetail').bootstrapTable(
        {
            columns : [
                {
                    field : 'ca_kuanhao',
                    title : '款号',
                    width : "250px",
                    halign :"center",
                    align : "center"
                    // valign : 'middle'
                    //sortable: true
                },
                {
                    field : 'ca_yanse',
                    title : '颜色',
                    width : "100px",
                    halign :"center",
                    align : "center"
                },
                {
                    field : 'ye_xs',
                    title : '业务XS/34',
                    width : "65px",
                    halign :"center",
                    align : "center",
                    cellStyle:function(value,row,index) {
                        return {
                            css: {
                                "font-weight":"bold",
                                "color":"#ff1111"
                            }
                        }
                    }
                },
                {
                    field : 'ca_xs',
                    title : '裁剪XS/34',
                    width : "65px",
                    halign :"center",
                    align : "center",
                    cellStyle:function(value,row,index) {
                        return {
                            css: {
                                "font-weight":"bold"
                            }
                        }
                    }
                },
                {
                    field : 'ye_s',
                    title : '业务S/36',
                    width : "65px",
                    halign :"center",
                    align : "center",
                    cellStyle:function(value,row,index) {
                        return {
                            css: {
                                "font-weight":"bold",
                                "color":"#ff1111"
                            }
                        }
                    }
                },
                {
                    field : 'ca_s',
                    title : '裁剪S/36',
                    width : "65px",
                    halign :"center",
                    align : "center",
                    cellStyle:function(value,row,index) {
                        return {
                            css: {
                                "font-weight":"bold"
                            }
                        }
                    }
                },
                {
                    field : 'ye_m',
                    title : '业务M/38',
                    width : "65px",
                    halign :"center",
                    align : "center",
                    cellStyle:function(value,row,index) {
                        return {
                            css: {
                                "font-weight":"bold",
                                "color":"#ff1111"
                            }
                        }
                    }
                },
                {
                    field : 'ca_m',
                    title : '裁剪M/38',
                    width : "65px",
                    halign :"center",
                    align : "center",
                    cellStyle:function(value,row,index) {
                        return {
                            css: {
                                "font-weight":"bold"
                            }
                        }
                    }
                },
                {
                    field : 'ye_l',
                    title : '业务L/40',
                    width : "65px",
                    halign :"center",
                    align : "center",
                    cellStyle:function(value,row,index) {
                        return {
                            css: {
                                "font-weight":"bold",
                                "color":"#ff1111"
                            }
                        }
                    }
                },
                {
                    field : 'ca_l',
                    title : '裁剪L/40',
                    width : "65px",
                    halign :"center",
                    align : "center",
                    cellStyle:function(value,row,index) {
                        return {
                            css: {
                                "font-weight":"bold"
                            }
                        }
                    }
                },
                {
                    field : 'ye_xl',
                    title : '业务XL/42',
                    width : "65px",
                    halign :"center",
                    align : "center",
                    cellStyle:function(value,row,index) {
                        return {
                            css: {
                                "font-weight":"bold",
                                "color":"#ff1111"
                            }
                        }
                    }
                },
                {
                    field : 'ca_xl',
                    title : '裁剪XL/42',
                    width : "65px",
                    halign :"center",
                    align : "center",
                    cellStyle:function(value,row,index) {
                        return {
                            css: {
                                "font-weight":"bold"
                            }
                        }
                    }
                },
                {
                    field : 'ye_xxl',
                    title : '业务XXL/44',
                    width : "65px",
                    halign :"center",
                    align : "center",
                    cellStyle:function(value,row,index) {
                        return {
                            css: {
                                "font-weight":"bold",
                                "color":"#ff1111"
                            }
                        }
                    }
                },
                {
                    field : 'ca_xxl',
                    title : '裁剪XXL/44',
                    width : "65px",
                    halign :"center",
                    align : "center",
                    cellStyle:function(value,row,index) {
                        return {
                            css: {
                                "font-weight":"bold"
                            }
                        }
                    }
                },
                {
                    field : 'ye_xxxl',
                    title : '业务XXXL/46',
                    width : "65px",
                    halign :"center",
                    align : "center",
                    cellStyle:function(value,row,index) {
                        return {
                            css: {
                                "font-weight":"bold",
                                "color":"#ff1111"
                            }
                        }
                    }
                },
                {
                    field : 'ca_xxxl',
                    title : '裁剪XXXL/46',
                    width : "65px",
                    halign :"center",
                    align : "center",
                    cellStyle:function(value,row,index) {
                        return {
                            css: {
                                "font-weight":"bold"
                            }
                        }
                    }
                }],
            locale : 'zh-CN',
            url : 'all',
            method : 'GET',
            queryParams : queryParams,
            sidePagination : "server",
            contentType: "application/x-www-form-urlencoded",
            dataType : 'json',
            pagination : true,
            pageNumber : 1,
            pageSize : 5,
            pageList : [ 5, 10, 25, 50, 100 ],
            clickToSelect : true
        }
    );
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

}

// 添加供应商模态框数据校验
function bootstrapValidatorInit() {
    $("#goods_form").bootstrapValidator({
        message: 'This is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        excluded: [':disabled'],
        fields: {
            cjriqi: {
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

function addNew() {
    $('#add_modal_submit').click(function() {
        var msg = "操作失败";//非submit按钮点击后进行验证，如果是submit则无需此句直接验证
        $("#goods_form").bootstrapValidator('validate');//提交验证
        if ($("#goods_form").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
            data = {
                id : data.id,
                kuanhao : data.kuanhao,
                cjriqi : $('#cjriqi').val(),
                yanse : data.yanse,
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
                url : "insert",
                dataType : "json",
                contentType : "application/json",
                data : JSON.stringify(data),
                success : function(response) {
                    $('#add_modal').modal("hide");
                    var type;
                    var append = '';
                    if (response.result == "success") {
                        type = "success";
                        msg = "操作成功";
                    } else if (response.result != "success") {
                        type = "error";
                        msg = "操作失败";
                    }
                    alert(msg);//验证成功后的操作，如ajax
                    tableRefresh();

                    // reset
                    dateNow();
                    $('#xs').val("0");
                    $('#s').val("0");
                    $('#m').val("0");
                    $('#l').val("0");
                    $('#xl').val("0");
                    $('#xxl').val("0");
                    $('#xxxl').val("0");
                    $('#goods_form').bootstrapValidator("resetForm", false);
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

// 添加货物信息
function addGoodsAction(row) {
    $('#add_modal').modal("show");
    dateNow();
    data = {
        id : row.id,
        kuanhao : row.kuanhao,
        yanse : row.yanse
    }
}

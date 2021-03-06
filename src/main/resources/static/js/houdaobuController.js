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
    editCustomerAction();
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
    $('#hdriqi').val(time);
}

// 分页查询参数
function queryParams(params) {
    var temp = {
        limit : params.limit,
        offset : params.offset,
        searchType : search_type_goods,
        keyWord : search_keyWord,
        sortOrder: params.order,//排序
        sortName:params.sort//排序字段
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
                        title : 'ID',
                        sortable: true
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
                        sortable: true
                    },
                    {
                        field : 'cjbshuliang',
                        title : '裁剪部数量',
                        width : "150px",
                        halign :"center",
                        align : "center"
                    },

                    {
                        field : 'hdzonghe',
                        title : '后道数量',
                        halign :"center",
                        align : "center"
                    },
                    {
                        field : 'operation',
                        title : '操作',
                        halign :"center",
                        align : "center",
                        formatter : function(value, row, index) {
                            var s = '<button class="btn btn-info" id="edit" style="padding-left: 50px;padding-right: 50px"><span>查看详情</span></button>';
                            var sd = '<button class="btn btn-primary" id="cjLinkhd" style="margin-left: 20px;padding-left: 30px;padding-right: 30px"><span>比较裁剪部数量</span></button>';
                            var de = '<button class="btn btn-success" id="myModalLabel" style="margin-left: 20px;padding-left: 50px;padding-right: 50px"><span>添加详情</span></button>';
                            // var d = '<button class="btn btn-danger btn-sm delete"><span>删除</span></button>';
                            // var fun = '';
                            return s + ' ' + sd + ' ' + de  ;
                        },
                        events : {
                            // 操作列中编辑按钮的动作
                            'click #edit': function (e, value,
                                                     row, index) {
                                selectID = row.id;
                                search_keyWord = selectID;
                                search_type_goods = "findByKuanhaoYanse";
                                showCaiJian();
                                infotableRefresh();
                            },
                            'click #cjLinkhd': function (e, value,
                                                         row, index) {
                                selectID = row.id;
                                search_keyWord = selectID;
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
                    sumZH(json);
                },
                onLoadError:function(status){
                    handleAjaxError(status);
                },
                method : 'GET',
                locale : 'zh-CN',
                sortName : 'id',
                sortOrder : 'desc',
                queryParams : queryParams,
                sidePagination : "server",
                offset : 0,
                limit : 5,
                contentType: "application/x-www-form-urlencoded",
                dataType : 'json',
                pagination : true,
                pageNumber : 1,
                pageSize : 10,
                pageList : [10],
                clickToSelect : true
            });
}

function showCaiJian() {
    $('#show_modal').modal("show");
    $('#show_modal').modal({backdrop: 'static', keyboard: false});
    $('#show_modal_submit').click(function(){
        $('#show_modal').modal("hide");
        search_type_goods = "searchAll";});
    $('#showdetail')
        .bootstrapTable(
            {
                columns : [
                    {
                        field : 'id',
                        title : 'ID'
                    },
                    {
                        field : 'kuanhao',
                        title : '款号',
                        width : "250px",
                        halign :"center",
                        align : "center"
                    },
                    {
                        field : 'hdriqi',
                        title : '后道日期',
                        width : "150px",
                        halign :"center",
                        align : "center"
                    },
                    {
                        field : 'xxs',
                        title : '后道XXS/32/0',
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
                        field : 'xs',
                        title : '后道XS/34/1',
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
                        field : 's',
                        title : '后道 S/36/2',
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
                        field : 'm',
                        title : '后道 M/38/3',
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
                        field : 'l',
                        title : '后道 L/40/4',
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
                        field : 'xl',
                        title : '后道 XL/42/5',
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
                        field : 'xxl',
                        title : '后道 XXL/44/6',
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
                        field : 'xxxl',
                        title : '后道 XXXL/46/7',
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
                        field : 'beizhu',
                        title : '备注',
                        halign :"center",
                        align : "center",
                    },
                    {
                        field : 'operation',
                        title : '操作',
                        halign :"center",
                        align : "center",
                        formatter : function(value, row, index) {
                            var d = '<button class="btn btn-success btn-sm delete"><span>修改</span></button>';
                            return d  ;
                        },
                        events : {
                            // 操作列中编辑按钮的动作
                            'click .delete': function (e, value,
                                                       row, index) {
                                selectID = row.id;
                                $('#kuanhao_edit').val(row.kuanhao);
                                $('#hdriqi_edit').val(row.hdriqi);
                                $('#yanse_edit').val(row.yanse);
                                $('#xxs_edit').val(row.xxs);
                                $('#xs_edit').val(row.xs);
                                $('#s_edit').val(row.s);
                                $('#m_edit').val(row.m);
                                $('#l_edit').val(row.l);
                                $('#xl_edit').val(row.xl);
                                $('#xxl_edit').val(row.xxl);
                                $('#xxxl_edit').val(row.xxxl);
                                $('#beizhu_edit').val(row.beizhu);
                                $('#edit_goods_modal').modal(
                                    'show');
                            }
                        }
                    }],
                locale : 'zh-CN',
                url : 'all',
                sortName : 'hdriqi',
                sortOrder : 'desc',
                method : 'GET',
                queryParams : queryParams,
                sidePagination : "server",
                offset : 0,
                limit : 5,
                contentType: "application/x-www-form-urlencoded",
                dataType : 'json',
                pagination : true,
                pageNumber : 1,
                pageSize : 10,
                pageList : [10],
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
    search_type_goods = "findByKuanhao";
    $('#edit_modal_submit').click(function(){
        $('#edit_modal').modal("hide");
        search_type_goods = "searchAll";
    });
    $('#goodsdetail').bootstrapTable(
        {
            columns : [
                {
                    field : 'ho_kuanhao',
                    title : '款号',
                    width : "100px"
                    //sortable: true
                },
                {
                    field : 'ho_yanse',
                    title : '颜色',
                    width : "100px"
                },
                {
                    field : 'ca_xxs',
                    title : '裁剪 XXS/32/0',
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
                    field : 'ho_xxs',
                    title : '后道 XXS/32/0',
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
                    field : 'ca_xs',
                    title : '裁剪 XS/34/1',
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
                    field : 'ho_xs',
                    title : '后道 XS/34/1',
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
                    field : 'ca_s',
                    title : '裁剪 S/36/2',
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
                    field : 'ho_s',
                    title : '后道 S/36/2',
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
                    field : 'ca_m',
                    title : '裁剪 M/38/3',
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
                    field : 'ho_m',
                    title : '后道 M/38/3',
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
                    field : 'ca_l',
                    title : '裁剪 L/40/4',
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
                    field : 'ho_l',
                    title : '后道 L/40/4',
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
                    field : 'ca_xl',
                    title : '裁剪 XL/42/5',
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
                    field : 'ho_xl',
                    title : '后道 XL/42/5',
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
                    field : 'ca_xxl',
                    title : '裁剪 XXL/44/6',
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
                    field : 'ho_xxl',
                    title : '后道 XXL/44/6',
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
                    field : 'ca_xxxl',
                    title : '裁剪 XXXL/46/7',
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
                    field : 'ho_xxxl',
                    title : '后道 XXXL/46/7',
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
            sortName : 'kuanhao',
            sortOrder : 'asc',
            method : 'GET',
            queryParams : queryParams,
            sidePagination : "server",
            offset : 0,
            limit : 5,
            contentType: "application/x-www-form-urlencoded",
            dataType : 'json',
            pagination : true,
            pageNumber : 1,
            pageSize : 10,
            pageList : [10],
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
    //         pageSize : 10,
    //         pageList : [ 5, 10, 25, 50, 100 ],
    //         clickToSelect : true
    //     }
    // );

}

function sumZH(response) {
    $('#sumZH').text("后道部数量总和："+response.sum);
}

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
            hdriqi: {
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
            xxs: {
                validators: {
                    notEmpty: {
                        message: '尺码不能为空，如没有请输入0'
                    },
                    regexp: {
                        regexp: '^[0-9]*$',
                        message: '只能输入0-9的整数，不能输入特殊字符'
                    }
                }
            },
            xs : {
                validators: {
                    notEmpty: {
                        message: '尺码不能为空，如没有请输入0'
                    },
                    regexp: {
                        regexp: '^[0-9]*$',
                        message: '只能输入0-9的整数，不能输入特殊字符'
                    }
                }
            },
            s : {
                validators: {
                    notEmpty: {
                        message: '尺码不能为空，如没有请输入0'
                    },
                    regexp: {
                        regexp: '^[0-9]*$',
                        message: '只能输入0-9的整数，不能输入特殊字符'
                    }
                }
            },
            m : {
                validators: {
                    notEmpty: {
                        message: '尺码不能为空，如没有请输入0'
                    },
                    regexp: {
                        regexp: '^[0-9]*$',
                        message: '只能输入0-9的整数，不能输入特殊字符'
                    }
                }
            },
            l : {
                validators: {
                    notEmpty: {
                        message: '尺码不能为空，如没有请输入0'
                    },
                    regexp: {
                        regexp: '^[0-9]*$',
                        message: '只能输入0-9的整数，不能输入特殊字符'
                    }
                }
            },
            xl : {
                validators: {
                    notEmpty: {
                        message: '尺码不能为空，如没有请输入0'
                    },
                    regexp: {
                        regexp: '^[0-9]*$',
                        message: '只能输入0-9的整数，不能输入特殊字符'
                    }
                }
            },
            xxl : {
                validators: {
                    notEmpty: {
                        message: '尺码不能为空，如没有请输入0'
                    },
                    regexp: {
                        regexp: '^[0-9]*$',
                        message: '只能输入0-9的整数，不能输入特殊字符'
                    }
                }
            },
            xxxl : {
                validators: {
                    notEmpty: {
                        message: '尺码不能为空，如没有请输入0'
                    },
                    regexp: {
                        regexp: '^[0-9]*$',
                        message: '只能输入0-9的整数，不能输入特殊字符'
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
                hdriqi : $('#hdriqi').val(),
                yanse : data.yanse,
                xxs : $('#xxs').val(),
                xs : $('#xs').val(),
                s : $('#s').val(),
                m : $('#m').val(),
                l :$('#l').val(),
                xl : $('#xl').val(),
                xxl : $('#xxl').val(),
                xxxl : $('#xxxl').val(),
                beizhu : $('#beizhu').val()

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
                    $('#xxs').val("0");
                    $('#xs').val("0");
                    $('#s').val("0");
                    $('#m').val("0");
                    $('#l').val("0");
                    $('#xl').val("0");
                    $('#xxl').val("0");
                    $('#xxxl').val("0");
                    $('#beizhu').val("");
                    $('#goods_form').data("bootstrapValidator").resetForm();

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

// 修改后道信息
function editCustomerAction(){
    $('#edit_goods_modal_submit').click(function(){
        var msg = "后道信息修改失败";
        data = {
            id : selectID,
            kuanhao : $('#kuanhao_edit').val(),
            hdriqi : $('#hdriqi_edit').val(),
            yanse : $('#yanse_edit').val(),
            xxs : $('#xxs_edit').val(),
            xs : $('#xs_edit').val(),
            s : $('#s_edit').val(),
            m : $('#m_edit').val(),
            l :$('#l_edit').val(),
            xl : $('#xl_edit').val(),
            xxl : $('#xxl_edit').val(),
            xxxl : $('#xxxl_edit').val(),
            beizhu : $('#beizhu_edit').val()
        }

        // ajax
        $.ajax({
            type : "POST",
            url : "update",
            dataType : "json",
            contentType : "application/json",
            data : JSON.stringify(data),
            success : function(response){
                $('#edit_goods_modal').modal("hide");
                if(response.result == "success"){

                    msg = "后道信息修改成功";

                }else{

                    msg = "后道信息修改失败";

                }
                search_type_goods = "searchAll";
                search_keyWord = "";
                alert(msg);
                infotableRefresh();
                tableRefresh();
            },error : function(xhr, textStatus, errorThrown){
                $('#edit_goods_modal').modal("hide");
                tableRefresh();
                // handler error
                handleAjaxError(xhr.status)
            }
        })

        $('#edit_goods_modal').modal('hide');
    })
}
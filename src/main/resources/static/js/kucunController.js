/**
 * Created by DELL on 2019/4/25.
 */
var search_type_goods = "searchAll";
var search_keyWord = "";
var selectID;

$(function() {
    optionAction();
    searchAction();
    goodsListInit();
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
        pageSize : params.pageSize,
        pageNumber : params.pageNumber,
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
                    },
                    {
                        field : 'kuanhao',
                        title : '款号'
                        //sortable : true
                    },
                    {
                        field : 'kcshuliang',
                        title : '库存数量'
                    },
                    {
                        field : 'operation',
                        title : '操作',
                        formatter : function(value, row, index) {
                            var s = '<button class="btn btn-info btn-sm edit"><span>查看</span></button>';
                            // var d = '<button class="btn btn-danger btn-sm delete"><span>删除</span></button>';
                            // var fun = '';
                            return s ;
                        },
                        events : {
                            // 操作列中编辑按钮的动作
                            'click .edit': function (e, value,
                                                     row, index) {
                                selectID = row.kuanhao;
                                search_keyWord = selectID;
                                detailTableRefresh();
                                rowEditOperation(row);
                            }
                        }
                    }],
                url : 'all',
                onLoadSuccess:function(json){
                    $("#goodsList").bootstrapTable('load',json);
                },
                onLoadError:function(status){
                    handleAjaxError(status);
                },
                locale : 'zh-CN',
                //sortName : 'id',
                //sortOrder : 'asc',
                method : 'GET',
                queryParams : queryParams,
                sidePagination : "server",
                contentType: "application/x-www-form-urlencoded",
                dataType : 'json',
                pagination : true,
                pageNumber : 1,
                pageSize : 5,
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
        search_type_goods = "searchAll";});
    $('#goodsdetail').bootstrapTable(
        {
            columns : [
                {
                    field : 'kuanhao',
                    title : '款号'
                    //sortable: true
                },
                {
                    field : 'yanse',
                    title : '颜色'
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
                }],
            locale : 'zh-CN',
            url : 'findByKuanhao',
            method : 'GET',
            queryParams : queryParams,
            sidePagination : "server",
            contentType: "application/x-www-form-urlencoded",
            dataType : 'json',
            pagination : true,
            pageNumber : 1,
            pageSize : 5,
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
    //         pageSize : 5,
    //         pageList : [ 5, 10, 25, 50, 100 ],
    //         clickToSelect : true
    //     }
    // );

}


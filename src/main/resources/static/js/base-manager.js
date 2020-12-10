(function () {
    var Base = Base || {};
    // 回显
    Base.ShowBack = {
        // 下拉框回显
        select: function (selectId, valId, defaultVal) {
            var chooseVal = $("#" + valId).val();
            if (Bee.StringUtils.isNotBlank(chooseVal)) {
                $("#" + selectId).val(chooseVal);
            } else {
                if (Bee.StringUtils.isNotBlank(defaultVal)) {
                    $("#" + selectId).val(defaultVal);
                }
            }
        },
        // 多选下拉框
        selectMultiple: function (selectId, valStr, split) {
            var array = valStr.split(split)
            $('#' + selectId).selectpicker('val', array);
        }
    };
    // 控制台打印
    Base.Console = {
        info: function (name, obj) {
            var currentTime = Bee.DateUtils.getCurrentTime();
            console.info("--->>>" + currentTime + "--->>> 控制台打印--->>>" + name + "--->>>")
            console.info(obj)
            console.info("--->>>" + currentTime + "--->>> 控制台打印结束-↑↑")
        },
        intoObj: function (name, obj) {
            var currentTime = Bee.DateUtils.getCurrentTime();
            console.info("--->>>" + currentTime + "--->>> 进入了条件/循环--->>>" + name + "---|||条件值是--->>" + obj);
        },
        into: function () {
            var currentTime = Bee.DateUtils.getCurrentTime();
            console.info("--->>>" + currentTime + "--->>> 进入了条件/循环---|||");
        }
    };
    // 图片工具
    Base.Picture = {
        init: function (pictureId) {
            var initPic = new $WebUpload(pictureId);
            initPic.setUploadBarId("progressBar");
            initPic.init();
        },
        convertBase64UrlToBlob: function (urlData, split) {
            //去掉url的头，并转换为byte
            var bytes = window.atob(urlData.split(split)[1]);
            //处理异常,将ascii码小于0的转换为大于0
            var ab = new ArrayBuffer(bytes.length);
            var ia = new Uint8Array(ab);
            for (var i = 0; i < bytes.length; i++) {
                ia[i] = bytes.charCodeAt(i);
            }
            return new Blob([ab], {type: 'image/png'});
        },
        // 获取CourseImg中的地址信息,返回字符串, 以 split 分割
        getCourseImg: function (className, split, formData) {
            var imgList = document.getElementsByClassName(className);
            var imgListOld = "";
            for (var i = 0; i < imgList.length; i++) {
                var str = imgList[i].src;
                if (str.indexOf("http") === 0) {
                    imgListOld = imgListOld + str + split;
                } else {
                    formData.append(className + i, Base.Picture.convertBase64UrlToBlob(str, split));
                }
            }
            if (Bee.StringUtils.isNotBlank(imgListOld)) {
                formData.append(className + "Old", imgListOld);
                formData.append("defaultOld", imgListOld);
            }
            return formData;
        },
    };
    Base.Array = {
        indexOfByKeyVal: function (array, key, val) {
            for (var i = 0; i < array.length; i++) {
                if (array[i][key] === val) return i;
            }
            return -1;
        },
        indexOf: function (array, ele) {
            for (let i = 0; i < array.length; i++) {
                if (array[i] === ele) return i;
            }
            return -1;
        },
        del: function (array, ele) {
            let index = Base.Array.indexOf(array, ele);
            if (index > -1) {
                array.splice(index, 1);
            }
        },
        delByKeyVal: function (array, key, val) {
            let index = Base.Array.indexOfByKeyVal(array, key, val)
            if (index > -1) {
                array.splice(index, 1)
            }
        },
        copy: function (sourceArray) {
            return JSON.parse(JSON.stringify(sourceArray));
        },
        empty: function (array) {
            array.splice(0, array.length);
        }
    };
    // switch 控件
    Base.Switch = {
        // 初始化
        init: function (id, checkedTxt, uncheckedTxt) {
            $('#' + id).bootstrapSwitch({
                onText: checkedTxt,
                offText: uncheckedTxt,
            });
        },
        // 禁用
        disable: function (id, isChecked) {
            $('#' + id).bootstrapSwitch('state', isChecked);
            $('#' + id).bootstrapSwitch('disabled', true);
        },
        showBack: function (id, isChecked) {
            $('#' + id).bootstrapSwitch('state', isChecked);
        }
    };
    // 验证工具
    Base.Validate = {
        passValid: true,
        init: function () {
            Base.Validate.passValid = true;
            return Base.Validate;
        },
        str: function (inputId, tip) {
            if (!Base.Validate.passValid) {
                return Base.Validate;
            }
            var val = $('#' + inputId).val();
            if (Bee.StringUtils.isBlank(val)) {
                Base.Validate.passValid = false;
                Feng.error(tip);
            } else {
                Base.Validate.passValid = true;
            }
            return Base.Validate;
        },
        strLen: function (inputId, minLen, maxLen, tip) {
            if (!Base.Validate.passValid) {
                return Base.Validate;
            }
            var val = $('#' + inputId).val();
            var len = val.length;
            if (len < minLen || maxLen <= len) {
                Base.Validate.passValid = false;
                Feng.error(tip);
            }
            return Base.Validate;
        },
        strVal: function (val, tip) {
            if (!Base.Validate.passValid) {
                return Base.Validate;
            }
            if (Bee.StringUtils.isBlank(val)) {
                Base.Validate.passValid = false;
                Feng.error(tip);
            } else {
                Base.Validate.passValid = true;
            }
            return Base.Validate;
        },
        number: function (inputId, min, max, tip) {
            if (!Base.Validate.passValid) {
                return Base.Validate;
            }
            var val = 'NaN';
            if (Bee.StringUtils.isNumeric($('#' + inputId).val())) {
                val = $('#' + inputId).val() * 1;
            }
            let bool = !Bee.StringUtils.isNumeric(val) || val < min || max <= val;
            if (bool) {
                Base.Validate.passValid = false;
                Feng.error(tip);
            } else {
                Base.Validate.passValid = true;
            }
            return Base.Validate;
        },
        numberVal: function (val, min, max, tip) {
            if (!Base.Validate.passValid) {
                return Base.Validate;
            }
            val = val * 1;
            let bool = Bee.StringUtils.isBlank(val) || !Bee.StringUtils.isNumeric(val) || val < min || max <= val;
            if (bool) {
                Base.Validate.passValid = false;
                Feng.error(tip);
            } else {
                Base.Validate.passValid = true;
            }
            return Base.Validate;
        },
        courseImg: function (className, tip) {
            if (!Base.Validate.passValid) {
                return Base.Validate;
            }
            var bool = $('.' + className).size() < 1;
            if (bool) {
                Base.Validate.passValid = false;
                Feng.error(tip);
            } else {
                Base.Validate.passValid = true;
            }
            return Base.Validate;
        },
        responseOk: function (obj) {
            return obj.code == Feng.SUCCESS;
        }
    }
    // 表单工具
    Base.Form = {
        serializeObject:function (formId) {
            let o ={};
            $.each($('#'+formId).serializeArray(),function(index){
                if(o[this['name']]){
                    o[this['name']] = o[this['name']] +","+this['value'];
                }else{
                    o[this['name']] = this['value'];
                }
            });
            return o;

        },
        clearData: function (obj, infoDlg) {
            obj.infoDlg = {}
        },
        set: function (obj, infoDlg, key, val) {
            obj.infoDlg[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
            return obj;
        },
        close: function (parentWindow) {
            parent.layer.close(window.parent.parentWindow.layerIndex);
        },
        collectData: function (obj, infoDlg, formId) {
            x = $("#" + formId).serializeArray();
            $.each(x, function (i, field) {
                Base.Form.set(obj, infoDlg, field.name)
            });
        },
        getData: function (obj, infoDlg, formId) {
            Base.Form.clearData(obj, infoDlg);
            Base.Form.collectData(obj, infoDlg, formId);
        },
        // 填充商家
        appendBusiness: function () {
            var ajax = new $ax(Feng.ctxPath + "/business/list", function (data) {
                var str = "<option value=''>---请选择---</option>";
                if (data.total > 0) {
                    var rows = data.rows;
                    for (var i = 0; i < rows.length; ++i) {
                        str += "<option value='" + rows[i].id + "'>" + rows[i].name + "</option>";
                    }
                } else {
                    Feng.error('此会员编号没有商铺');
                }
                let idEle = $('#businessId');
                if (idEle.length > 0) {
                    idEle.empty();
                    idEle.append(str);
                }
                let classEle = $('.businessId');
                if (classEle.length > 0) {
                    classEle.empty();
                    classEle.append(str);
                }
            }, function (data) {
                Feng.error("查询失败!" + data.msg + "!");
            });
            var param = {
                memberNo: $('#memberNo').val(),
                pageSize: 1000,
            };
            ajax.set(param);
            ajax.start();
        },
        // 填充活动
        appendActivity: function () {
            let ajax = new $ax(Feng.ctxPath + "/activityRule/list", function (data) {
                let str = "<option value=''>---请选择活动规则---</option>";
                if (data.total > 0) {
                    let rows = data.rows;
                    for (let i = 0; i < rows.length; ++i) {
                        str += "<option value='" + rows[i].id + "'>" + rows[i].title + "</option>";
                    }
                } else {
                    Feng.error('此会员编号没有活动规则');
                }
                let idEle = $('#activityRuleId');
                if (idEle.length > 0) {
                    idEle.empty();
                    idEle.append(str);
                }
                let classEle = $('.activityRuleId');
                if (classEle.length > 0) {
                    classEle.empty();
                    classEle.append(str);
                }
            }, function (data) {
                Feng.error("查询失败!" + data.msg + "!");
            });
            let param = {
                businessNo: $('#memberNo').val(),
            };
            ajax.set(param);
            ajax.start();
        },
        // 填充单位
        appendUnitSelect: function () {
            let ajax = new $ax(Feng.ctxPath + "/unit/list", function (data) {
                let str = "<option value=''>---请选择单位---</option>";
                data.rows.forEach(function (valRow) {
                    str += "<option value='" + valRow.name + "'>" + valRow.name + "</option>";
                });
                let idEle = $('#unitSelect');
                if (idEle.length > 0) {
                    idEle.empty();
                    idEle.append(str);
                }
                let classEle = $('.unitSelect');
                if (classEle.length > 0) {
                    classEle.empty();
                    classEle.append(str);
                }
            }, function (data) {
                Feng.error("单位初始化失败!" + data.msg + "!");
            });
            ajax.start();
        },
        appendUnit: function () {
            let ajax = new $ax(Feng.ctxPath + "/unit/list", function (data) {
                let str = '';
                data.rows.forEach(function (valRow) {
                    str += '<li class="unitOption"><a href="javascript:void(0);">' + valRow.name + '</a></li>';
                });
                let idEle = $('#unitSelect');
                if (idEle.length > 0) {
                    idEle.empty();
                    idEle.append(str);
                }
                let classEle = $('.unitSelect');
                if (classEle.length > 0) {
                    classEle.empty();
                    classEle.append(str);
                }
            }, function (data) {
                Feng.error("单位初始化失败!" + data.msg + "!");
            });
            ajax.start();
        },
        // 填充分成规则
        appendDivide: function () {
            var ajax = new $ax(Feng.ctxPath + "/divideRule/list", function (data) {
                var str = "<option value=''>---请选择分成规则---</option>";
                if (data.total > 0) {
                    var rows = data.rows;
                    for (var i = 0; i < rows.length; ++i) {
                        str += "<option value='" + rows[i].id + "'>" + rows[i].title + "</option>";
                    }
                } else {
                    Feng.error('此会员编号没有分成规则');
                }
                let idEle = $('#divideRuleId');
                if (idEle.length > 0) {
                    idEle.empty();
                    idEle.append(str);
                }
                let classEle = $('.divideRuleId');
                if (classEle.length > 0) {
                    classEle.empty();
                    classEle.append(str);
                }
            }, function (data) {
                Feng.error("查询失败!" + data.msg + "!");
            });
            var param = {
                businessNo: $('#memberNo').val(),
            };
            ajax.set(param);
            ajax.start();
        },
        // 生成ID
        generatorId: function () {
            var timestamp = Date.parse(new Date()) + '';
            for (i = 0; i < 18; i++) {
                timestamp += Math.floor(Math.random() * 10)
            }
            return timestamp;
        }
    };
    // 搜索条件
    Base.Search = {
        check: function () {
            var searchCondition = $('.searchCondition');
            var show = searchCondition.prop('isShow');
            if (show) {
                return true;
            } else {
                searchCondition.show(1000);
                searchCondition.prop('isShow', true);
                var str = '<span class="hideSearchConditionSpan hideSearchCondition">隐藏</span>'
                $('#searchBtnFromTag').append($(str));
                return false;
            }
        }
    };
    // 表格
    Base.Table = {
        isDisable: function () {
            let disabled = $('.selected :first').find('[name="btSelectItem"]').prop('disabled');
            if (disabled) {
                $('.selected :first').find('[name="btSelectItem"]').prop('checked', false);
                Feng.info("无法操作此数据！");
                return true;
            }
            return false;
        },
        isNotDisable: function () {
            return !Base.Table.isDisable();
        },
        // 表格单选检查
        hasChose: function (thisObj) {
            let selected = $('#' + thisObj.id).bootstrapTable('getSelections');
            if (selected.length === 0) {
                Feng.info("请先选中表格中的某一记录！");
                return false;
            } else {
                let disabled = $('.selected :first').find('[name="btSelectItem"]').prop('disabled');
                if (disabled) {
                    $('.selected :first').find('[name="btSelectItem"]').prop('checked', false);
                    Feng.info("无法操作此数据！");
                    return false;
                } else {
                    thisObj.seItem = selected[0];
                    return true;
                }
            }
        },
        //
        formatterDate: function (value) {
            var arr = value;
            if (arr == null || arr == "") {
                return "";
            } else {
                for (var i = 0; i < arr.length; i++) {
                    if (arr[i].length == 1) {
                        arr[i] = "0" + arr[i];
                    }
                }
                if (arr.length == 5) {
                    var getFormatTime = arr[0] + "-" + arr[1] + "-" + arr[2] + "\t" + arr[3] + ":" + arr[4] + ":" + "00";
                } else {
                    var getFormatTime = arr[0] + "-" + arr[1] + "-" + arr[2] + "\t" + arr[3] + ":" + arr[4] + ":" + arr[5];
                }

                return getFormatTime;
            }

        },
        // 表格多选检查
        hasChoseMultiple: function (thisObj, min, max) {
            if (typeof (min) == "undefined" || isNaN(min) || min < 1) {
                min === 1;
            }
            if (typeof (max) == "undefined" || isNaN(max) || max < min) {
                max === 25535;
            }
            var selected = $('#' + thisObj.id).bootstrapTable('getSelections');
            if (selected.length > max) {
                Feng.info("最多只能选择" + max + "条记录,请重新选择!");
                return false;
            }
            if (selected.length < 1) {
                Feng.info("请先选中表格中的某一记录！");
                return false;
            } else {
                thisObj.seItem = selected[0];
                var seItemsId = "";
                for (var i = 0; i < selected.length; i++) {
                    seItemsId = seItemsId + "," + selected[i].sourceId
                }
                seItemsId = seItemsId.substring(1, seItemsId.length);
                thisObj.seItems = seItemsId;
                return true;
            }
        }
    };
    // layer
    Base.Layer = {
        open: function (title, absolutePath) {
            var index = layer.open({
                type: 2,
                //弹窗标题
                title: title,
                //设定宽高比例，按百分比或是具体的宽高值
                area: ['100%', '100%'],
                //不固定
                fix: false,
                //是否最大化
                maxmin: true,
                //访问路径
                content: Feng.ctxPath + absolutePath,
            });
            return index;
        },
        initProductSort: function (infoDlg, val) {
            if (Bee.StringUtils.isNotBlank($("#parentId").val())) {
                $("#parentId").attr("value", "");
                $("#parentName").attr("value", "");
            }
            var subjectId = val;
            var ztree = new $ZTree("pCodeTree", "/sort/tree?subject=" + subjectId);
            ztree.bindOnClick(function (e, treeId, treeNode) {
                $("#parentName").attr("value", infoDlg.ztreeInstance.getSelectedVal());
                $("#parentId").attr("value", treeNode.id);
                $("#parentId").change();
            });
            ztree.init();
            infoDlg.ztreeInstance = ztree;
        },
        // 产品分类树节点初始化加载  请确认相关id一致 使用SelectCon标签
        treeProductSort: function (infoDlg) {
            layui.use(['form'], function () {
                var form = layui.form;
                //监听联动时间
                form.on('select(subject)', function (data) {
                    Base.Layer.initProductSort(infoDlg, data.value);
                });
                form.render();
            });
        }
    };
    // 常量
    Base.Constant = {
        JSON_CONTENT_TYPE: 'application/json;charset=utf-8',
        URL_CONTENT_TYPE: 'application/x-www-form-urlencoded;charset=utf-8',
    };
    Base.Network = {

        post: function (opt) {
            let url = opt.url
            let successParse = opt.successParse || function () {
            };
            let failParse = opt.failParse || function (data) {
                console.error(data);
                console.error(data.msg);
                $.modal.msgError(data.msg)
            };
            let type = opt.type || 'post'
            let contentType = opt.contentType || 'application/x-www-form-urlencoded'
            if(opt.contentType === 'application/json') {
                opt.data = JSON.stringify(opt.data);
            }
            $.ajax({
                url: opt.url,
                data: opt.data,
                contentType: contentType,
                type: type,
                async: false, //改为同步
                success: function (httpResponse) {
                    if (httpResponse.code === '000000') {
                        let data = httpResponse.data
                        successParse(data);
                    } else {
                        failParse(httpResponse);
                    }
                },
                error: function (data) {
                    console.log(data);
                },
            });
        },
        postJSON:function(){

        },
    }
    window['Base'] = Base;
})();
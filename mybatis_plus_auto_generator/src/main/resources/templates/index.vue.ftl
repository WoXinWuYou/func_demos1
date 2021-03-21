<template>
  <div class="wrapper_content">
    <div class="header clear">
      <p class="header_title fl">物资管理/ <i>${table.comment!}</i></p>
    </div>
    <div class="content">
      <div class="header_search clear">
        <el-form :inline="true" :model="formInlines" class="demo-form-inline">
          <#list table.fields as field>
            <el-form-item label="">
              <el-input
                      v-model="formInlines.${field.propertyName}"
                      placeholder="${field.comment}"
              ></el-input>
            </el-form-item>
          </#list>
          <el-form-item>
            <div class="search_btn" @click="getlist">查询</div>
            <div class="search_btn" @click="resetlist">重置</div>
          </el-form-item>
          <el-form-item class="fr">
            <div class="search_btn" @click="addmodal">
              <img src="../../../../assets/index/icon_add.png" alt="" />
              新增
            </div>
          </el-form-item>
        </el-form>
      </div>
      <div class="list_wrapper">
        <el-table
          :data="tableData"
          style="width: 100%"
          height="calc(100% - 40px)"
          stripe
        >

          <#list table.fields as field>
            <el-table-column prop="${field.propertyName}" label="${field.comment}"></el-table-column>
          </#list>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <div
                class="search_btn search_btn_small"
                @click.native.stop="saveedit(scope.$index, scope.row)"
              >
                <img src="../../../../assets/index/icon_alter.png" alt="" /> 修改
              </div>
              <div
                class="search_btn search_btn_small"
                @click.native.stop="savedetails(scope.$index, scope.row)"
              >
                <img src="../../../../assets/index/icon_detail.png" alt="" /> 详情
              </div>
              <div
                class="search_btn search_btn_small"
                @click.native.stop="savedel(scope.$index, scope.row)"
              >
                <img src="../../../../assets/index/icon_detail.png" alt="" /> 删除
              </div>
            </template>
          </el-table-column>
        </el-table>
        <div class="paginationblock">
          <pagination
            v-show="length > 0"
            :total="length"
            :page.sync="pageIndex"
            :limit.sync="pageSize"
            @pagination="handlePageChange"
          />
        </div>
      </div>
    </div>
    <a-modal
      v-model="visible"
      :title="modeltit == 'add' ? '添加${table.comment!}' : '编辑${table.comment!}'"
      @ok="savesubmit('add')"
      width="50%"
      okText="确认"
      cancelText="取消"
    >
      <el-form :model="form" label-width="120px">

        <#list table.fields as field>
          <el-form-item label="${field.comment}">
            <el-input v-model="form.${field.propertyName}" autocomplete="off"></el-input>
          </el-form-item>
        </#list>
      </el-form>
    </a-modal>
    <a-modal
      v-model="detvisible"
      title="${table.comment!}详情"
      width="50%"
      cancelText="取消"
    >
      <el-form :model="forminfo" label-width="120px">
        <#list table.fields as field>
          <el-form-item label="${field.comment}">
            <el-input v-model="form.${field.propertyName}" autocomplete="off" readonly></el-input>
          </el-form-item>
        </#list>
      </el-form>
    </a-modal>
  </div>
</template>

<script>
import Pagination from "@/unit/Pagination";
export default {
  components: { Pagination },
  name: "unregisteredPeople",
  data() {
    return {
      formInlines: {
      <#list table.fields as field>
        ${field.propertyName} : "" ,
      </#list>
      },
      visible: false,
      detvisible: false,
      length: 0,
      pageSize: 20, //要传过去的数据 每页多少条数据
      pageIndex: 0, //要传过去的数据 第几页
      page: 1, // 实际页码
      tableData: [],
      form: {
      <#list table.fields as field>
        ${field.propertyName} : "" ,
      </#list>
      },
      forminfo:{
<#list table.fields as field>
        ${field.propertyName} : "" ,
      </#list>
      },
      modeltit: "add",
    };
  },
  mounted() {
    var _this = this;
     _this.getlist(); // 获取物资
  },

  methods: {
    // 分页
    handlePageChange(data) {
      this.pageIndex = data.pageIndex;
      this.pageSize = data.pageSize;
      this.getlist(); //这是重新获取数据的方法
    },
    getlist() {
      // 获取${table.comment!}列表
      var _this = this;
      _this.$axios
        .post(
          "/${table.entityPath}/list",
         {
              <#list table.fields as field>
                  ${field.propertyName} : _this.formInlines.${field.propertyName} ,
              </#list>
            currPage: _this.page,
            pagesize: _this.pageSize,
          }
        )
        .then((res) => {
          //   console.log(res);
          if (res.data.status == 1) {
            let data = res.data.data.list;
            _this.tableData = data;
            _this.length = res.data.data.count;
          } else {
            _this.$alert(res.data.statusDesc, "提示");
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    // 弹窗确认提交
    savesubmit() {
      let _this = this;
      let data = {
           <#list table.fields as field>
                  ${field.propertyName} : _this.formInlines.${field.propertyName} ,
              </#list>
        };
      let url ;
      if (_this.modeltit == "add") {
        url = "/${table.entityPath}/add"
      } else if (_this.modeltit == "edit") {
         url = "/${table.entityPath}/update"
      }
      _this.$axios
        .post(
          url,
          data
        )
        .then((res) => {
          //   console.log(res);
          if (res.data.status == 1) {
            if (_this.modeltit == "add") {
              _this.$alert("新增成功", "提示");
            } else if (_this.modeltit == "edit") {
              _this.$alert("修改成功", "提示");
            }
            _this.form = {
            <#list table.fields as field>
              ${field.propertyName} : "" ,
            </#list>
            };
            _this.visible = false;
            _this.getlist();
          } else {
            _this.$alert(res.data.statusDesc, "提示");
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    // 点击新增
    addmodal() {
      this.form = {
      <#list table.fields as field>
        ${field.propertyName} : "" ,
      </#list>
      };
      this.visible = true;
      this.modeltit = "add";
    },
    // 点击修改
    saveedit(index, row) {
       this.form = {
            <#list table.fields as field>
                ${field.propertyName} : row.${field.propertyName} ,
            </#list>
             };
      this.visible = true;
      this.modeltit = "edit";
    },
    // 查看详情
    savedetails(index, row) {
      var _this = this;
      _this.$axios
        .post(
          "/${table.entityPath}/del",
         {
            id: row.id,
          }
        )
        .then((res) => {
          //   console.log(res);
          if (res.data.status == 1) {
            _this.$alert(res.data.statusDesc, "提示");
             this.forminfo = {
            <#list table.fields as field>
                ${field.propertyName} :res.data.data.${field.propertyName},
            </#list>
             };
            _this.detvisible = true;
          } else {
            _this.$alert(res.data.statusDesc, "提示");
          }
        })
        .catch((e) => {
          console.log(e);
        });
      _this.detvisible = true;
    },
    savedel(index, row) {
      var _this = this;
      _this.$axios
        .post(
          "/${table.entityPath}/del",
         {
            id: row.id,
          }
        )
        .then((res) => {
          //   console.log(res);
          if (res.data.status == 1) {
            _this.$alert("删除成功", "提示");
            _this.getlist();
          } else {
            _this.$alert(res.data.statusDesc, "提示");
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    // 重置
    resetlist() {
      this.formInlines = {
        <#list table.fields as field>
          ${field.propertyName} : "" ,
        </#list>
      };
      this.getlist();
    },
  },
};
</script>

<style scoped lang="less">
.wrapper_content {
  width: 98%;
  height: calc(100% - 54px);
  overflow: hidden;
  z-index: 1;
  margin-left: 30px;
  position: relative;
}
.header {
  margin: 10px 0;
  .header_title {
    text-align: left;
    font-size: 15px;
    height: 30px;
    line-height: 30px;
    color: #37bfff;
  }
}
.content {
  width: 100%;
  padding: 20px;
  height: calc(100% - 50px);
  background: rgba(2, 25, 57, 0.8);
  border: 1px solid rgba(3, 175, 247, 0.16);
  .header_search {
    // margin-bottom: 10px;
    .search_content {
      margin: 0 10px;
    }
  }
  .list_wrapper {
    height: calc(100% - 40px);
    overflow: auto;
  }
}
</style>

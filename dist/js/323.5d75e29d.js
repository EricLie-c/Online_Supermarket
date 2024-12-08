"use strict";(self["webpackChunkonline_suppermarket"]=self["webpackChunkonline_suppermarket"]||[]).push([[323],{1323:function(t,e,i){i.r(e),i.d(e,{default:function(){return m}});var a=function(){var t=this,e=t._self._c;return e("div",[e("el-container",{staticStyle:{height:"100%"}},[e("el-header",{staticStyle:{display:"flex","align-items":"center","justify-content":"center","background-color":"#4f7dbd52"}},[e("i",{staticClass:"el-icon-shopping-cart-2",staticStyle:{"font-size":"4.5rem",color:"#222","margin-right":"22%"}}),e("span",{staticStyle:{"font-size":"50px","font-family":"'Courier New', Courier, monospace","margin-right":"22%"}},[t._v("在 线 商 城")]),e("el-popover",{attrs:{placement:"top",width:"160"},model:{value:t.popoverVisible,callback:function(e){t.popoverVisible=e},expression:"popoverVisible"}},[e("p",{staticStyle:{"text-align":"center"}},[t._v("确定退出登录？")]),e("div",{staticStyle:{"text-align":"center",margin:"0"}},[e("el-button",{attrs:{size:"mini",type:"text"},on:{click:function(e){t.popoverVisible=!1}}},[t._v("取 消")]),e("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(e){t.dialogFormVisible=!0}}},[t._v("确 定")])],1),e("el-button",{staticStyle:{"background-color":"azure"},attrs:{slot:"reference",icon:"el-icon-user"},slot:"reference"},[e("span",{domProps:{textContent:t._s(t.userName)}})])],1),e("el-dialog",{staticStyle:{left:"25%",top:"10%","text-align":"center",width:"800px"},attrs:{title:"登 录",visible:t.dialogFormVisible,"show-close":!1,"close-on-click-modal":!1},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[e("el-form",[e("el-form-item",{attrs:{label:"账 号","label-width":"50px"}},[e("el-input",{staticStyle:{width:"300px","text-align-last":"center"},attrs:{autocomplete:"off"},model:{value:t.signIn.account,callback:function(e){t.$set(t.signIn,"account",e)},expression:"signIn.account"}})],1),e("el-form-item",{attrs:{label:"密 码","label-width":"50px"}},[e("el-input",{staticStyle:{width:"300px","text-align-last":"center"},attrs:{autocomplete:"off","show-password":""},model:{value:t.signIn.password,callback:function(e){t.$set(t.signIn,"password",e)},expression:"signIn.password"}})],1),e("el-select",{staticStyle:{"text-align-last":"center"},attrs:{placeholder:"选择身份"},model:{value:t.signIn.role,callback:function(e){t.$set(t.signIn,"role",e)},expression:"signIn.role"}},[e("el-option",{attrs:{label:"商 家",value:"merchant"}},[t._v(" 商 家 ")]),e("el-option",{attrs:{label:"顾 客",value:"guest"}},[t._v(" 顾 客 ")])],1)],1),e("div",{staticStyle:{"text-align":"center"},attrs:{slot:"footer"},slot:"footer"},[e("el-button",{attrs:{type:"primary"},on:{click:function(e){t.dialogFormVisible=!1,t.submitRegistry()}}},[t._v("确 定")]),e("el-button",{on:{click:function(e){t.dialogFormVisible=!1}}},[t._v("取 消")]),e("el-button",{attrs:{type:"warning"},on:{click:function(e){t.dialogFormVisible=!1,t.deleteRegistry()}}},[t._v("注 销")])],1)],1),e("i",{staticClass:"el-icon-s-custom",staticStyle:{"margin-left":"2%"}})],1),e("el-container",[e("el-aside",{staticStyle:{"background-color":"#4f7dbd82",height:"100%"},attrs:{width:"230px"}},[e("el-menu",{staticStyle:{"background-color":"#4f7dbd82"},attrs:{"default-openeds":["1","2"]}},[e("el-submenu",{attrs:{index:"1"}},[e("template",{slot:"title"},[e("i",{staticClass:"el-icon-message"}),t._v("商家特权")]),e("el-menu-item-group",{staticStyle:{"background-color":"#4f7dbd79"}},[e("template",{slot:"title"},[e("span",{staticStyle:{color:"#222"}},[t._v("订单")])]),e("el-menu-item",{attrs:{index:"1-1"}},[e("router-link",{staticStyle:{color:"black","text-decoration":"none"},attrs:{to:"/orderManagement"}},[t._v(" 订单管理 ")])],1),e("el-menu-item",{attrs:{index:"1-2"}},[e("router-link",{staticStyle:{color:"black","text-decoration":"none"},attrs:{to:"/statisticalForm"}},[t._v(" 统计报表 ")])],1)],2),e("el-menu-item-group",{staticStyle:{"background-color":"#4f7dbd79"}},[e("template",{slot:"title"},[e("span",{staticStyle:{color:"#222"}},[t._v("客户")])]),e("el-menu-item",{attrs:{index:"1-3"}},[e("router-link",{staticStyle:{color:"black","text-decoration":"none"},attrs:{to:"/guestManagement"}},[t._v(" 客户管理 ")])],1),e("el-menu-item",{attrs:{index:"1-4"}},[e("router-link",{staticStyle:{color:"black","text-decoration":"none"},attrs:{to:"/guestLog"}},[t._v(" 客户日志 ")])],1)],2),e("el-menu-item-group",{staticStyle:{"background-color":"#4f7dbd79"}},[e("template",{slot:"title"},[e("span",{staticStyle:{color:"#222"}},[t._v("商品")])]),e("el-menu-item",{attrs:{index:"1-5"}},[e("el-button",{staticStyle:{color:"black"},attrs:{type:"text"},on:{click:function(e){t.controlCommodityVisible=!0}}},[t._v(" 商品目录 ")]),e("el-dialog",{staticStyle:{"text-align-last":"center",left:"25%",top:"10%",width:"800px"},attrs:{title:"商品目录管理",visible:t.controlCommodityVisible},on:{"update:visible":function(e){t.controlCommodityVisible=e}}},[e("el-button",{attrs:{type:"primary"},on:{click:function(e){t.addItemVisible=!0}}},[t._v("添加"),e("i",{staticClass:"el-icon-upload el-icon--right"})]),e("el-button",{attrs:{type:"primary"},on:{click:function(e){t.deleteItemVisible=!0}}},[t._v("删除"),e("i",{staticClass:"el-icon-delete el-icon--right"})]),e("el-button",{attrs:{type:"primary"},on:{click:function(e){t.editItemVisible=!0}}},[t._v("修改"),e("i",{staticClass:"el-icon-edit el-icon--right"})])],1),e("el-dialog",{staticStyle:{"text-align-last":"center"},attrs:{title:"添加商品",visible:t.addItemVisible},on:{"update:visible":function(e){t.addItemVisible=e}}},[t._v(" 品名  "),e("el-input",{staticStyle:{width:"400px"},model:{value:t.itemInfo.name,callback:function(e){t.$set(t.itemInfo,"name",e)},expression:"itemInfo.name"}}),e("br"),t._v(" 价格  "),e("el-input",{staticStyle:{width:"400px"},model:{value:t.itemInfo.price,callback:function(e){t.$set(t.itemInfo,"price",e)},expression:"itemInfo.price"}}),e("br"),t._v(" 地址  "),e("el-input",{staticStyle:{width:"400px"},model:{value:t.itemInfo.addr,callback:function(e){t.$set(t.itemInfo,"addr",e)},expression:"itemInfo.addr"}}),e("br"),t._v(" 时间  "),e("el-input",{staticStyle:{width:"400px"},model:{value:t.itemInfo.time,callback:function(e){t.$set(t.itemInfo,"time",e)},expression:"itemInfo.time"}}),e("br"),e("br"),e("el-upload",{ref:"upload",attrs:{action:"",drag:"",multiple:"","auto-upload":!1,"on-change":t.fileChange}},[e("i",{staticClass:"el-icon-upload"}),e("div",{staticClass:"el-upload__text"},[t._v("将商品图片拖到此处，或"),e("em",[t._v("点击上传")])]),e("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[t._v("只能上传jpg/png文件，且不超过500kb")])]),e("el-button",{attrs:{type:"primary"},on:{click:function(e){t.submitItem("add"),t.addItemVisible=!1}}},[t._v(" 确认 ")])],1),e("el-dialog",{staticStyle:{"text-align-last":"center"},attrs:{title:"修改商品",visible:t.editItemVisible},on:{"update:visible":function(e){t.editItemVisible=e}}},[t._v(" 原名称  "),e("el-input",{staticStyle:{width:"400px"},model:{value:t.itemInfo.pastname,callback:function(e){t.$set(t.itemInfo,"pastname",e)},expression:"itemInfo.pastname"}}),e("br"),t._v(" 修改名称  "),e("el-input",{staticStyle:{width:"400px"},model:{value:t.itemInfo.name,callback:function(e){t.$set(t.itemInfo,"name",e)},expression:"itemInfo.name"}}),e("br"),t._v(" 修改价格  "),e("el-input",{staticStyle:{width:"400px"},model:{value:t.itemInfo.price,callback:function(e){t.$set(t.itemInfo,"price",e)},expression:"itemInfo.price"}}),e("br"),t._v(" 修改地址  "),e("el-input",{staticStyle:{width:"400px"},model:{value:t.itemInfo.addr,callback:function(e){t.$set(t.itemInfo,"addr",e)},expression:"itemInfo.addr"}}),e("br"),t._v(" 修改时间  "),e("el-input",{staticStyle:{width:"400px"},model:{value:t.itemInfo.time,callback:function(e){t.$set(t.itemInfo,"time",e)},expression:"itemInfo.time"}}),e("br"),e("br"),e("el-upload",{ref:"upload",attrs:{action:"",drag:"",multiple:"","auto-upload":!1,"on-change":t.fileChange}},[e("i",{staticClass:"el-icon-upload"}),e("div",{staticClass:"el-upload__text"},[t._v("将商品图片拖到此处，或"),e("em",[t._v("点击上传")])]),e("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[t._v("只能上传jpg/png文件，且不超过500kb")])]),e("el-button",{attrs:{type:"primary"},on:{click:function(e){t.submitItem("edit"),t.editItemVisible=!1}}},[t._v(" 确认 ")])],1),e("el-dialog",{staticStyle:{"text-align-last":"center"},attrs:{title:"删除商品",visible:t.deleteItemVisible},on:{"update:visible":function(e){t.deleteItemVisible=e}}},[t._v(" 商品名称  "),e("el-input",{staticStyle:{width:"400px"},model:{value:t.itemInfo.name,callback:function(e){t.$set(t.itemInfo,"name",e)},expression:"itemInfo.name"}}),e("br"),e("br"),e("el-button",{attrs:{type:"primary"},on:{click:function(e){t.submitItem("delete"),t.deleteItemVisible=!1}}},[t._v(" 确认 ")])],1)],1)],2)],2)],1)],1),e("el-main",{staticStyle:{"background-color":"#4f7dbd65"}},[e("el-form",{staticStyle:{"text-align":"center"},attrs:{inline:!0,model:t.searchForm,size:"mini"}},[e("el-form-item",[e("template",{slot:"label"},[e("span",{staticStyle:{color:"#222"}},[t._v("客户日志")])]),e("el-select",{attrs:{placeholder:"浏览/购买"},model:{value:t.searchForm.watchOrBuy,callback:function(e){t.$set(t.searchForm,"watchOrBuy",e)},expression:"searchForm.watchOrBuy"}},[e("el-option",{attrs:{label:"浏览记录",value:"watch"}}),e("el-option",{attrs:{label:"购买记录",value:"buy"}})],1)],2),e("el-form-item",[e("el-button",{attrs:{type:"primary"},on:{click:function(e){t.onSubmit(),t.displayTable()}}},[t._v("查询")])],1)],1),t.orderData.length>0?e("el-table",{staticStyle:{"margin-top":"20px",color:"#222"},attrs:{data:t.orderData,"header-cell-style":{backgroundColor:"#4f7dbd85",color:"#222"},"cell-style":{backgroundColor:"#4f7dbd68",color:"#222"}}},t._l(t.tableHeaders,(function(i,a){return e("el-table-column",{key:a,attrs:{prop:t.tableFormat[a],label:i}})})),1):t._e(),e("br"),e("br"),e("br"),e("br"),e("div",{attrs:{id:"pagination"}},[e("el-pagination",{attrs:{background:"",layout:"prev, pager, next, jumper",total:1e3},on:{"current-change":t.handleCurrentChange}})],1)],1)],1)],1)],1)},l=[],o=i(4373),n={data(){return{userName:"",orderData:[],searchForm:{watchOrBuy:""},dialogFormVisible:!1,popoverVisible:!1,signIn:{account:"",password:"",role:""},tableHeaders:[],tableFormat:[],controlCommodityVisible:!1,addItemVisible:!1,deleteItemVisible:!1,editItemVisible:!1,pictureFile:{},itemInfo:{name:"",price:"",addr:"",status:""}}},methods:{onSubmit:function(){o.A.post("http://113.44.84.103:8080/orderManagement",this.searchForm).then((t=>{this.orderData=t.data.data}))},submitRegistry:function(){o.A.post("http://113.44.84.103:8080/changeAccount",this.signIn).then((t=>{1==t.data.status?this.userName=t.data.userName:alert("该用户不存在"),"guest"==this.signIn.role&&1==t.data.status&&location.replace("http://113.44.84.103:7000/#/guest")}))},handleCurrentChange:function(t){this.searchForm["user"]=this.userName,this.searchForm["page"]=t,o.A.post("http://113.44.84.103:8080/orderManagement",this.searchForm).then((t=>{this.orderData=t.data.data}))},displayTable(){"watch"===this.searchForm.watchOrBuy?(this.tableHeaders=["浏览日期","客户名","商品名"],this.tableFormat=["watchday","gname","pname"]):(this.tableHeaders=["购买日期","客户名","商品名","数量","金额￥"],this.tableFormat=["orderday","gname","pname","nums","amount"])},fileChange(t){const e=["image/png","image/jpeg","image/jpg"],i=-1!==e.indexOf(t.raw.type),a=t.size/1024/1024<3;return i?a?(this.pictureFile=t.raw,void console.log(t)):(this.$message.error("上传图片大小不能超过 3MB!"),this.$refs.upload.clearFiles(),void(this.pictureFile=null)):(this.$message.error("只能是图片!"),this.$refs.upload.clearFiles(),void(this.pictureFile=null))},submitItem(t){this.itemInfo.status=t;const e=new FormData;Object.keys(this.itemInfo).forEach((t=>{e.append(t,this.itemInfo[t])})),"delete"!=this.itemInfo.status&&e.append("file",this.pictureFile),o.A.post("http://113.44.84.103:8080/changeCommodity",e).then((()=>{alert("操作成功！")})),this.pictureFile={},this.itemInfo={},this.$refs.upload.clearFiles()},deleteRegistry(){o.A.get("http://113.44.84.103:8080/deleteUser",{params:{userName:this.userName}}).then((()=>{alert("注销成功！"),location.replace("http://113.44.84.103:7000/#/")}))}},mounted(){this.searchForm["page"]=1,this.searchForm["forWhat"]="guestLog",o.A.post("http://113.44.84.103:8080/orderManagement",this.searchForm).then((t=>{this.orderData=t.data.data})),o.A.get("http://113.44.84.103:8080/username").then((t=>this.userName=t.data.userName))}},s=n,r=i(1656),c=(0,r.A)(s,a,l,!1,null,null,null),m=c.exports}}]);
//# sourceMappingURL=323.5d75e29d.js.map
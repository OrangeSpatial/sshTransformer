(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["about"],{"0ead":function(e,t,n){"use strict";n("9239")},"74fc":function(e,t,n){"use strict";n("c8d5")},"8b83":function(e,t,n){"use strict";n.r(t);var a=n("7a23"),o=Object(a["S"])("data-v-7f1c94b5");Object(a["D"])("data-v-7f1c94b5");var l={class:"node"},u=Object(a["k"])("windows"),c=Object(a["k"])("linux"),r=Object(a["k"])(" 添加 ");Object(a["B"])();var d=o((function(e,t,n,d,i,s){var f=Object(a["H"])("a-input"),b=Object(a["H"])("a-form-item"),m=Object(a["H"])("a-select-option"),j=Object(a["H"])("a-select"),p=Object(a["H"])("a-button"),O=Object(a["H"])("a-form"),v=Object(a["H"])("a-table");return Object(a["A"])(),Object(a["j"])("div",l,[Object(a["l"])(O,{layout:"inline",model:d.formState,onFinish:d.handleFinish,onFinishFailed:d.handleFinishFailed},{default:o((function(){return[Object(a["l"])(b,null,{default:o((function(){return[Object(a["l"])(f,{value:d.formState.nodeName,"onUpdate:value":t[1]||(t[1]=function(e){return d.formState.nodeName=e}),placeholder:"节点名称"},null,8,["value"])]})),_:1}),Object(a["l"])(b,null,{default:o((function(){return[Object(a["l"])(f,{value:d.formState.remotePath,"onUpdate:value":t[2]||(t[2]=function(e){return d.formState.remotePath=e}),placeholder:"目标路径"},null,8,["value"])]})),_:1}),Object(a["l"])(b,null,{default:o((function(){return[Object(a["l"])(f,{value:d.formState.host,"onUpdate:value":t[3]||(t[3]=function(e){return d.formState.host=e}),placeholder:"主机IP"},null,8,["value"])]})),_:1}),Object(a["l"])(b,null,{default:o((function(){return[Object(a["l"])(f,{value:d.formState.user,"onUpdate:value":t[4]||(t[4]=function(e){return d.formState.user=e}),placeholder:"用户名"},null,8,["value"])]})),_:1}),Object(a["l"])(b,null,{default:o((function(){return[Object(a["l"])(f,{type:"password",value:d.formState.password,"onUpdate:value":t[5]||(t[5]=function(e){return d.formState.password=e}),placeholder:"密码"},null,8,["value"])]})),_:1}),Object(a["l"])(b,null,{default:o((function(){return[Object(a["l"])(j,{value:d.formState.systemType,"onUpdate:value":t[6]||(t[6]=function(e){return d.formState.systemType=e}),style:{width:"200px"},placeholder:"选择主机系统类型"},{default:o((function(){return[Object(a["l"])(m,{value:0},{default:o((function(){return[u]})),_:1}),Object(a["l"])(m,{value:1},{default:o((function(){return[c]})),_:1})]})),_:1},8,["value"])]})),_:1}),Object(a["l"])(b,null,{default:o((function(){return[Object(a["l"])(p,{type:"primary","html-type":"submit"},{default:o((function(){return[r]})),_:1})]})),_:1})]})),_:1},8,["model","onFinish","onFinishFailed"]),Object(a["l"])(v,{columns:d.columns,"data-source":d.data,"row-key":function(e){return e.id}},{action:o((function(e){var t=e.record;return[Object(a["l"])("a",{onClick:function(e){return d.delNode(t.id)}},"删除",8,["onClick"])]})),type:o((function(e){var t=e.text;return[Object(a["k"])(Object(a["J"])(0===t?"Windows":"Linux"),1)]})),_:1},8,["columns","data-source","row-key"])])})),i=n("f64c"),s=[{title:"任务名称",dataIndex:"nodeName",key:"nodeName"},{title:"目标路径",dataIndex:"remotePath",key:"remotePath"},{title:"主机IP",dataIndex:"host",key:"host"},{title:"用户名",dataIndex:"user",key:"user"},{title:"系统类型",dataIndex:"systemType",key:"systemType",slots:{customRender:"type"}},{title:"操作",dataIndex:"",key:"x",slots:{customRender:"action"}}],f={name:"Home",components:{},setup:function(){var e=Object(a["E"])({nodeName:"",remotePath:"",host:"",user:"",password:"",systemType:null}),t=function(t){console.log(t,e),l.$http.post("/node/add",e).then((function(e){console.log(e.data),i["a"].success("添加成功！"),c()}))},n=function(e){console.log(e)},o=Object(a["n"])(),l=o.proxy,u=Object(a["F"])([]),c=function(){l.$http.get("/node/all").then((function(e){console.log(e.data),u.value=e.data}))},r=function(e){l.$http.get("/node/del/"+e).then((function(e){if(console.log(e.data),e.data)return c(),i["a"].success("删除成功！")}))};return Object(a["y"])((function(){c()})),{formState:e,handleFinish:t,handleFinishFailed:n,columns:s,data:u,getAllNodes:c,delNode:r}}};n("0ead");f.render=d,f.__scopeId="data-v-7f1c94b5";t["default"]=f},9239:function(e,t,n){},c8d5:function(e,t,n){},eff4:function(e,t,n){"use strict";n.r(t);var a=n("7a23"),o=Object(a["S"])("data-v-24099e27");Object(a["D"])("data-v-24099e27");var l={class:"resource"};Object(a["B"])();var u=o((function(e,t,n,o,u,c){var r=Object(a["H"])("a-table");return Object(a["A"])(),Object(a["j"])("div",l,[Object(a["l"])(r,{columns:o.columns,"data-source":o.data,"row-key":function(e){return e.id}},null,8,["columns","data-source","row-key"])])})),c=[{title:"文件名",dataIndex:"name",key:"name"},{title:"文件路径",dataIndex:"path",key:"path"},{title:"文件大小",dataIndex:"size",key:"size"}],r={name:"Home",components:{},setup:function(){var e=Object(a["n"])(),t=e.proxy,n=Object(a["F"])([]),o=function(){t.$http.get("/file/all").then((function(e){console.log(e.data),n.value=e.data}))};return Object(a["y"])((function(){o()})),{columns:c,data:n}}};n("74fc");r.render=u,r.__scopeId="data-v-24099e27";t["default"]=r}}]);
//# sourceMappingURL=about.ad399b3b.js.map
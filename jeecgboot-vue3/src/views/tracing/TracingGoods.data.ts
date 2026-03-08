import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '标签分类',
    align:"center",
    dataIndex: 'tags_dictText'
   },
  {
    title: '所属用户',
    align:"center",
    dataIndex: 'userId_dictText'
   },
   {
    title: '物品名称',
    align:"center",
    dataIndex: 'name'
   },
   {
    title: '存放位置',
    align:"center",
    dataIndex: 'location'
   },
   {
    title: '存放详细位置',
    align:"center",
    dataIndex: 'locationDetail'
   },
   {
    title: '备注',
    align:"center",
    dataIndex: 'remark'
   },
   {
    title: '图片预览',
    align:"center",
    dataIndex: 'imgPath'
   }
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '所属用户',
    field: 'userId',
    component: 'JSelectUser',
    componentProps: {
      labelKey: 'realname',
      rowKey: 'id',
      isRadioSelection: true
    }
  },
  {
    label: '标签分类',
    field: 'tags',
    component: 'JTreeSelect',
    componentProps: {
      dict: 'tb_tracing_tag,name,id',
      pidField: "parent_id",
      multiple: true
    }
  },
  {
    label: '物品名称',
    field: 'name',
    component: 'Input',
  },
  {
    label: '存放位置',
    field: 'location',
    component: 'Input',
  },
  {
    label: '存放详细位置',
    field: 'locationDetail',
    component: 'Input',
  },
  {
    label: '备注',
    field: 'remark',
    component: 'Input',
  },
  {
    label: '图片选择',
    field: 'imgPath',
    component: 'JImageUpload',
    helpMessage: '最多上传1张图片',
    componentProps: {
      fileMax : 1,
      bizPath: 'traceGoods'
    }
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];

// 高级查询数据
// export const superQuerySchema = {
//   tenantId: {title: '租户id',order: 0,view: 'number', type: 'number',},
//   status: {title: '状态',order: 1,view: 'number', type: 'number',},
//   name: {title: '物品名称',order: 3,view: 'text', type: 'string',},
//   location: {title: '存放位置',order: 4,view: 'text', type: 'string',},
//   locationDetail: {title: '存放详细位置',order: 5,view: 'text', type: 'string',},
//   tags: {title: '标签分类',order: 6,view: 'text', type: 'string',},
//   remark: {title: '备注',order: 7,view: 'text', type: 'string',},
//   imgPath: {title: '图片路径',order: 8,view: 'text', type: 'string',},
// };

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}
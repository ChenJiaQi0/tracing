import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '标签名',
    align:"center",
    dataIndex: 'name'
   },
   {
    title: '父级名',
    align:"center",
    dataIndex: 'parentId_dictText'
   },
   {
    title: '备注',
    align:"center",
    dataIndex: 'remark'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '标签名',
    field: 'name',
    component: 'Input',
  },
  {
    label: '父级',
    field: 'parentId',
    component: 'JSearchSelect',
    // colProps: { span: 20 },
    componentProps: {
      dict: 'tb_tracing_tag,name,id,parent_id is null'
    }
  },
  {
    label: '备注',
    field: 'remark',
    component: 'Input',
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
export const superQuerySchema = {
  tenantId: {title: '租户id',order: 0,view: 'number', type: 'number',},
  status: {title: '状态',order: 1,view: 'number', type: 'number',},
  name: {title: '标签名',order: 3,view: 'text', type: 'string',},
  parentId: {title: '父级id',order: 4,view: 'text', type: 'string',},
  remark: {title: '备注',order: 5,view: 'text', type: 'string',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}
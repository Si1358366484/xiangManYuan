// 字典工具函数集合

/**
 * 创建字典映射
 * @param {Array} data - 数据源数组
 * @param {string} keyField - 作为键的字段名
 * @param {string} valueField - 作为值的字段名
 * @returns {Object} 字典对象
 */
export const createDict = (data = [], keyField = 'id', valueField = 'name') => {
  return data.reduce((dict, item) => {
    dict[item[keyField]] = item[valueField]
    return dict
  }, {})
}

/**
 * 获取字典值
 * @param {Object} dict - 字典对象
 * @param {any} key - 要查找的键
 * @param {any} defaultValue - 默认值
 * @returns {any} 对应的字典值或默认值
 */
export const getDictValue = (dict = {}, key, defaultValue = '') => {
  return dict[key] !== undefined ? dict[key] : defaultValue
}

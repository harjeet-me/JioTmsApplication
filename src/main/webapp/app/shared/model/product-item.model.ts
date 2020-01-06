export interface IProductItem {
  id?: number;
  itemName?: string;
  description?: string;
  qty?: number;
  price?: number;
  discount?: number;
}

export class ProductItem implements IProductItem {
  constructor(
    public id?: number,
    public itemName?: string,
    public description?: string,
    public qty?: number,
    public price?: number,
    public discount?: number
  ) {}
}

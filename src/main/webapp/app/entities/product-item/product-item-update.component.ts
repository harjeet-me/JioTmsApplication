import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProductItem, ProductItem } from 'app/shared/model/product-item.model';
import { ProductItemService } from './product-item.service';

@Component({
  selector: 'jhi-product-item-update',
  templateUrl: './product-item-update.component.html'
})
export class ProductItemUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    itemName: [],
    description: [],
    qty: [],
    price: [],
    discount: []
  });

  constructor(protected productItemService: ProductItemService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productItem }) => {
      this.updateForm(productItem);
    });
  }

  updateForm(productItem: IProductItem): void {
    this.editForm.patchValue({
      id: productItem.id,
      itemName: productItem.itemName,
      description: productItem.description,
      qty: productItem.qty,
      price: productItem.price,
      discount: productItem.discount
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const productItem = this.createFromForm();
    if (productItem.id !== undefined) {
      this.subscribeToSaveResponse(this.productItemService.update(productItem));
    } else {
      this.subscribeToSaveResponse(this.productItemService.create(productItem));
    }
  }

  private createFromForm(): IProductItem {
    return {
      ...new ProductItem(),
      id: this.editForm.get(['id'])!.value,
      itemName: this.editForm.get(['itemName'])!.value,
      description: this.editForm.get(['description'])!.value,
      qty: this.editForm.get(['qty'])!.value,
      price: this.editForm.get(['price'])!.value,
      discount: this.editForm.get(['discount'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductItem>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}

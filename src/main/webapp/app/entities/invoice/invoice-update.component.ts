import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IInvoice, Invoice } from 'app/shared/model/invoice.model';
import { InvoiceService } from './invoice.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IReference } from 'app/shared/model/reference.model';
import { ReferenceService } from 'app/entities/reference/reference.service';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer/customer.service';
import { ITrip } from 'app/shared/model/trip.model';
import { TripService } from 'app/entities/trip/trip.service';

type SelectableEntity = IReference | ICustomer | ITrip;

@Component({
  selector: 'jhi-invoice-update',
  templateUrl: './invoice-update.component.html'
})
export class InvoiceUpdateComponent implements OnInit {
  isSaving = false;
  reference1s: IReference[] = [];
  reference2s: IReference[] = [];
  reference3s: IReference[] = [];
  customers: ICustomer[] = [];
  trips: ITrip[] = [];
  invoiceDateDp: any;
  invoicePaidDateDp: any;
  invoiceDueDateDp: any;

  editForm = this.fb.group({
    id: [],
    orderNo: [],
    taxRate: [],
    taxType: [],
    currency: [],
    invoiceTaxTotal: [],
    invoiceSubTotal: [],
    invoiceTotal: [],
    invoiceDate: [],
    invoicePaidDate: [],
    refValue1: [],
    refValue2: [],
    refValue3: [],
    payRefNo: [],
    invoiceDueDate: [],
    status: [],
    invoicePdf: [],
    invoicePdfContentType: [],
    remarks: [],
    reference1Id: [],
    reference2Id: [],
    reference3Id: [],
    customerId: [],
    tripId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected invoiceService: InvoiceService,
    protected referenceService: ReferenceService,
    protected customerService: CustomerService,
    protected tripService: TripService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invoice }) => {
      this.updateForm(invoice);

      this.referenceService
        .query({ filter: 'invoice-is-null' })
        .pipe(
          map((res: HttpResponse<IReference[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IReference[]) => {
          if (!invoice.reference1Id) {
            this.reference1s = resBody;
          } else {
            this.referenceService
              .find(invoice.reference1Id)
              .pipe(
                map((subRes: HttpResponse<IReference>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IReference[]) => (this.reference1s = concatRes));
          }
        });

      this.referenceService
        .query({ filter: 'invoice-is-null' })
        .pipe(
          map((res: HttpResponse<IReference[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IReference[]) => {
          if (!invoice.reference2Id) {
            this.reference2s = resBody;
          } else {
            this.referenceService
              .find(invoice.reference2Id)
              .pipe(
                map((subRes: HttpResponse<IReference>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IReference[]) => (this.reference2s = concatRes));
          }
        });

      this.referenceService
        .query({ filter: 'invoice-is-null' })
        .pipe(
          map((res: HttpResponse<IReference[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IReference[]) => {
          if (!invoice.reference3Id) {
            this.reference3s = resBody;
          } else {
            this.referenceService
              .find(invoice.reference3Id)
              .pipe(
                map((subRes: HttpResponse<IReference>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IReference[]) => (this.reference3s = concatRes));
          }
        });

      this.customerService.query().subscribe((res: HttpResponse<ICustomer[]>) => (this.customers = res.body || []));

      this.tripService.query().subscribe((res: HttpResponse<ITrip[]>) => (this.trips = res.body || []));
    });
  }

  updateForm(invoice: IInvoice): void {
    this.editForm.patchValue({
      id: invoice.id,
      orderNo: invoice.orderNo,
      taxRate: invoice.taxRate,
      taxType: invoice.taxType,
      currency: invoice.currency,
      invoiceTaxTotal: invoice.invoiceTaxTotal,
      invoiceSubTotal: invoice.invoiceSubTotal,
      invoiceTotal: invoice.invoiceTotal,
      invoiceDate: invoice.invoiceDate,
      invoicePaidDate: invoice.invoicePaidDate,
      refValue1: invoice.refValue1,
      refValue2: invoice.refValue2,
      refValue3: invoice.refValue3,
      payRefNo: invoice.payRefNo,
      invoiceDueDate: invoice.invoiceDueDate,
      status: invoice.status,
      invoicePdf: invoice.invoicePdf,
      invoicePdfContentType: invoice.invoicePdfContentType,
      remarks: invoice.remarks,
      reference1Id: invoice.reference1Id,
      reference2Id: invoice.reference2Id,
      reference3Id: invoice.reference3Id,
      customerId: invoice.customerId,
      tripId: invoice.tripId
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('jioTmsApplicationApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const invoice = this.createFromForm();
    if (invoice.id !== undefined) {
      this.subscribeToSaveResponse(this.invoiceService.update(invoice));
    } else {
      this.subscribeToSaveResponse(this.invoiceService.create(invoice));
    }
  }

  private createFromForm(): IInvoice {
    return {
      ...new Invoice(),
      id: this.editForm.get(['id'])!.value,
      orderNo: this.editForm.get(['orderNo'])!.value,
      taxRate: this.editForm.get(['taxRate'])!.value,
      taxType: this.editForm.get(['taxType'])!.value,
      currency: this.editForm.get(['currency'])!.value,
      invoiceTaxTotal: this.editForm.get(['invoiceTaxTotal'])!.value,
      invoiceSubTotal: this.editForm.get(['invoiceSubTotal'])!.value,
      invoiceTotal: this.editForm.get(['invoiceTotal'])!.value,
      invoiceDate: this.editForm.get(['invoiceDate'])!.value,
      invoicePaidDate: this.editForm.get(['invoicePaidDate'])!.value,
      refValue1: this.editForm.get(['refValue1'])!.value,
      refValue2: this.editForm.get(['refValue2'])!.value,
      refValue3: this.editForm.get(['refValue3'])!.value,
      payRefNo: this.editForm.get(['payRefNo'])!.value,
      invoiceDueDate: this.editForm.get(['invoiceDueDate'])!.value,
      status: this.editForm.get(['status'])!.value,
      invoicePdfContentType: this.editForm.get(['invoicePdfContentType'])!.value,
      invoicePdf: this.editForm.get(['invoicePdf'])!.value,
      remarks: this.editForm.get(['remarks'])!.value,
      reference1Id: this.editForm.get(['reference1Id'])!.value,
      reference2Id: this.editForm.get(['reference2Id'])!.value,
      reference3Id: this.editForm.get(['reference3Id'])!.value,
      customerId: this.editForm.get(['customerId'])!.value,
      tripId: this.editForm.get(['tripId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInvoice>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}

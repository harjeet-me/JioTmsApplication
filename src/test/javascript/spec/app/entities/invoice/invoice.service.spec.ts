import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { InvoiceService } from 'app/entities/invoice/invoice.service';
import { IInvoice, Invoice } from 'app/shared/model/invoice.model';
import { TaxType } from 'app/shared/model/enumerations/tax-type.model';
import { CURRENCY } from 'app/shared/model/enumerations/currency.model';
import { InvoiceStatus } from 'app/shared/model/enumerations/invoice-status.model';

describe('Service Tests', () => {
  describe('Invoice Service', () => {
    let injector: TestBed;
    let service: InvoiceService;
    let httpMock: HttpTestingController;
    let elemDefault: IInvoice;
    let expectedResult: IInvoice | IInvoice[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(InvoiceService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Invoice(
        0,
        'AAAAAAA',
        0,
        TaxType.GST,
        CURRENCY.USD,
        0,
        0,
        0,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        InvoiceStatus.DRAFT,
        'image/png',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            invoiceDate: currentDate.format(DATE_FORMAT),
            invoicePaidDate: currentDate.format(DATE_FORMAT),
            invoiceDueDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Invoice', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            invoiceDate: currentDate.format(DATE_FORMAT),
            invoicePaidDate: currentDate.format(DATE_FORMAT),
            invoiceDueDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            invoiceDate: currentDate,
            invoicePaidDate: currentDate,
            invoiceDueDate: currentDate
          },
          returnedFromService
        );

        service.create(new Invoice()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Invoice', () => {
        const returnedFromService = Object.assign(
          {
            orderNo: 'BBBBBB',
            taxRate: 1,
            taxType: 'BBBBBB',
            currency: 'BBBBBB',
            invoiceTaxTotal: 1,
            invoiceSubTotal: 1,
            invoiceTotal: 1,
            invoiceDate: currentDate.format(DATE_FORMAT),
            invoicePaidDate: currentDate.format(DATE_FORMAT),
            refValue1: 'BBBBBB',
            refValue2: 'BBBBBB',
            refValue3: 'BBBBBB',
            payRefNo: 'BBBBBB',
            invoiceDueDate: currentDate.format(DATE_FORMAT),
            status: 'BBBBBB',
            invoicePdf: 'BBBBBB',
            remarks: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            invoiceDate: currentDate,
            invoicePaidDate: currentDate,
            invoiceDueDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Invoice', () => {
        const returnedFromService = Object.assign(
          {
            orderNo: 'BBBBBB',
            taxRate: 1,
            taxType: 'BBBBBB',
            currency: 'BBBBBB',
            invoiceTaxTotal: 1,
            invoiceSubTotal: 1,
            invoiceTotal: 1,
            invoiceDate: currentDate.format(DATE_FORMAT),
            invoicePaidDate: currentDate.format(DATE_FORMAT),
            refValue1: 'BBBBBB',
            refValue2: 'BBBBBB',
            refValue3: 'BBBBBB',
            payRefNo: 'BBBBBB',
            invoiceDueDate: currentDate.format(DATE_FORMAT),
            status: 'BBBBBB',
            invoicePdf: 'BBBBBB',
            remarks: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            invoiceDate: currentDate,
            invoicePaidDate: currentDate,
            invoiceDueDate: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Invoice', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});

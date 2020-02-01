import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JioTmsApplicationTestModule } from '../../../test.module';
import { InvoiceRefDetailComponent } from 'app/entities/invoice-ref/invoice-ref-detail.component';
import { InvoiceRef } from 'app/shared/model/invoice-ref.model';

describe('Component Tests', () => {
  describe('InvoiceRef Management Detail Component', () => {
    let comp: InvoiceRefDetailComponent;
    let fixture: ComponentFixture<InvoiceRefDetailComponent>;
    const route = ({ data: of({ invoiceRef: new InvoiceRef(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JioTmsApplicationTestModule],
        declarations: [InvoiceRefDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(InvoiceRefDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InvoiceRefDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load invoiceRef on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.invoiceRef).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

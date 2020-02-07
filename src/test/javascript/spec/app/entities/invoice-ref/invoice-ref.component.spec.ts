import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JioTmsApplicationTestModule } from '../../../test.module';
import { InvoiceRefComponent } from 'app/entities/invoice-ref/invoice-ref.component';
import { InvoiceRefService } from 'app/entities/invoice-ref/invoice-ref.service';
import { InvoiceRef } from 'app/shared/model/invoice-ref.model';

describe('Component Tests', () => {
  describe('InvoiceRef Management Component', () => {
    let comp: InvoiceRefComponent;
    let fixture: ComponentFixture<InvoiceRefComponent>;
    let service: InvoiceRefService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JioTmsApplicationTestModule],
        declarations: [InvoiceRefComponent]
      })
        .overrideTemplate(InvoiceRefComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InvoiceRefComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InvoiceRefService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new InvoiceRef(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.invoiceRefs && comp.invoiceRefs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

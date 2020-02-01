import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JioTmsApplicationTestModule } from '../../../test.module';
import { InvoiceRefUpdateComponent } from 'app/entities/invoice-ref/invoice-ref-update.component';
import { InvoiceRefService } from 'app/entities/invoice-ref/invoice-ref.service';
import { InvoiceRef } from 'app/shared/model/invoice-ref.model';

describe('Component Tests', () => {
  describe('InvoiceRef Management Update Component', () => {
    let comp: InvoiceRefUpdateComponent;
    let fixture: ComponentFixture<InvoiceRefUpdateComponent>;
    let service: InvoiceRefService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JioTmsApplicationTestModule],
        declarations: [InvoiceRefUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(InvoiceRefUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InvoiceRefUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InvoiceRefService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new InvoiceRef(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new InvoiceRef();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});

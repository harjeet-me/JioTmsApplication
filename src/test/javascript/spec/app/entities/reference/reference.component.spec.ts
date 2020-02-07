import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JioTmsApplicationTestModule } from '../../../test.module';
import { ReferenceComponent } from 'app/entities/reference/reference.component';
import { ReferenceService } from 'app/entities/reference/reference.service';
import { Reference } from 'app/shared/model/reference.model';

describe('Component Tests', () => {
  describe('Reference Management Component', () => {
    let comp: ReferenceComponent;
    let fixture: ComponentFixture<ReferenceComponent>;
    let service: ReferenceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JioTmsApplicationTestModule],
        declarations: [ReferenceComponent]
      })
        .overrideTemplate(ReferenceComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ReferenceComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReferenceService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Reference(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.references && comp.references[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

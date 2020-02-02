import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JioTmsApplicationTestModule } from '../../../test.module';
import { EmailDetailComponent } from 'app/entities/email/email-detail.component';
import { Email } from 'app/shared/model/email.model';

describe('Component Tests', () => {
  describe('Email Management Detail Component', () => {
    let comp: EmailDetailComponent;
    let fixture: ComponentFixture<EmailDetailComponent>;
    const route = ({ data: of({ email: new Email(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JioTmsApplicationTestModule],
        declarations: [EmailDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EmailDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EmailDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load email on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.email).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});